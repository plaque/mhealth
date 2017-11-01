package plaque.mhealth.ui.pupil.fragments.pupils_meds

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.fragment_meds.*
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
class PupilsMedsFragment: RxBaseFragment(), NotesDelegateAdapter.onViewSelectedListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_meds, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        (meds_list.adapter as NotesAdapter).addNotes((activity as PupilDetailActivity).user.notes!!.toList())
        fab.hide()
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
        fun newInstance(): PupilsMedsFragment = PupilsMedsFragment()
    }

}