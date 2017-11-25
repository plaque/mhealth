package plaque.mhealth.ui.pupil.fragments.pupil_meds

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.fragment_meds.*
import kotlinx.android.synthetic.main.rv_item_meds.view.*
import org.jetbrains.anko.childrenSequence
import org.jetbrains.anko.find
import plaque.mhealth.R
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.ui.adapters.NotesAdapter
import plaque.mhealth.ui.adapters.NotesDelegateAdapter
import plaque.mhealth.ui.pupil.PupilDetailActivity
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment
import plaque.mhealth.ui.dialogs.NoteDetailDialog

/**
 * Created by szymon on 15.10.17.
 */
class PupilMedsFragment : RxBaseFragment(), NotesDelegateAdapter.onViewSelectedListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_meds, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        (meds_list.adapter as NotesAdapter).addNotes((activity as PupilDetailActivity).user.notes!!.toList())
        fab.hide()
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({blockCheckBoxes()}, 100 )
    }

    override fun onItemSelected(item: CyclicNote, position: Int) {
        var noteDialog = NoteDetailDialog()
        noteDialog.note = item
        noteDialog.position = position
        noteDialog.hideEditButton()
        noteDialog.show(fragmentManager, "")
    }

    private fun initAdapter(){

        meds_list.setHasFixedSize(true)
        meds_list.layoutManager = LinearLayoutManager(context)

        if(meds_list.adapter == null){
            meds_list.adapter = NotesAdapter(this)
        }
    }

    companion object {
        fun newInstance(): PupilMedsFragment = PupilMedsFragment()
    }

    private fun blockCheckBoxes(){
        for(i in 0..meds_list.childCount){
            ((meds_list.getChildAt(i) as? LinearLayout)?.getChildAt(2) as? CheckBox)?.isEnabled = false
        }
    }
}