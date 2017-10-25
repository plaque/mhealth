package plaque.mhealth.ui.user_main_slider.fragments.meds

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.fragment_meds.*
import plaque.mhealth.ui.adapters.NotesDelegateAdapter
import plaque.mhealth.ui.adapters.NotesAdapter
import plaque.mhealth.R
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.ui.notes.NewNoteDialog
import plaque.mhealth.ui.notes.NoteDetailDialog
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class MedsFragment: RxBaseFragment(), NotesDelegateAdapter.onViewSelectedListener, MedsView {

    @Inject lateinit var medsPresenter: MedsPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View? = inflater?.inflate(R.layout.fragment_meds, container, false)

        mHealthApp.medsComponent.inject(this)

        medsPresenter.setPresenterSubscriptions(subscriptions)
        medsPresenter.setView(this)


        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener{ _ -> medsPresenter.onFabClicked()}

    }

    override fun onDestroy() {
        super.onDestroy()
        medsPresenter.clearView()
        medsPresenter.closeRealm()
    }

    override fun onItemSelected(item: CyclicNote, position: Int) {
        medsPresenter.onNoteClicked(item, position)
    }

    override fun showNotes(notes: List<CyclicNote>) {
        (meds_list.adapter as NotesAdapter).addNotes(notes)
    }

    override fun showNoteDetails(note: CyclicNote, position: Int) {
        var noteDialog = NoteDetailDialog()
        noteDialog.note = note
        noteDialog.position = position
        noteDialog.show(fragmentManager, "")
    }


    override fun showAddNewNote() {
        val addNoteDialog = NewNoteDialog()
        addNoteDialog.show(fragmentManager, "")
    }

    fun onNotesChange(){
        medsPresenter.onNotesChange(((meds_list.adapter as NotesAdapter).getNotes() as ArrayList<CyclicNote>))
    }



    private fun initAdapter(){

        meds_list.setHasFixedSize(true)
        meds_list.layoutManager = LinearLayoutManager(context)

        if(meds_list.adapter == null){
            meds_list.adapter = NotesAdapter(this)
        }
    }

    companion object {
        fun newInstance(): MedsFragment = MedsFragment()
    }
}