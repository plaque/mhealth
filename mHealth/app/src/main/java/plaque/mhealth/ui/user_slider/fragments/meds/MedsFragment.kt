package plaque.mhealth.ui.user_slider.fragments.meds

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.fragment_meds.*
import plaque.mhealth.ui.adapters.NotesDelegateAdapter
import plaque.mhealth.ui.adapters.NotesAdapter
import plaque.mhealth.R
import plaque.mhealth.database.entities.UserEntity
import plaque.mhealth.ui.user_slider.fragments.RxBaseFragment
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.CyclicNote
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

    override fun onItemSelected(item: CyclicNote) {
        medsPresenter.onNoteClicked(item)
    }

    override fun showNotes(notes: List<CyclicNote>) {
        (meds_list.adapter as NotesAdapter).addNotes(notes)
    }

    override fun showNoteDetails(note: CyclicNote) {
        var noteDialog = NoteDetailDialog()
        noteDialog.note = note
        noteDialog.show(fragmentManager, "")
    }


    override fun showAddNewNote() {
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