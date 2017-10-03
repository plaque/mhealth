package plaque.mhealth.UI.UserSlider.Fragments.Meds

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.cyclic_note.*
import kotlinx.android.synthetic.main.one_time_note.*
import plaque.mhealth.UI.Adapters.NotesDelegateAdapter
import plaque.mhealth.UI.Adapters.NotesAdapter
import plaque.mhealth.Commons.getFullDate
import plaque.mhealth.Managers.MedsManager
import plaque.mhealth.Model.CyclicNote
import plaque.mhealth.Model.Note
import plaque.mhealth.Model.OneTimeNote
import plaque.mhealth.R
import plaque.mhealth.Retrofit.UserRestAPI
import plaque.mhealth.UI.UserSlider.Fragments.RxBaseFragment
import plaque.mhealth.mHealthApp
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class MedsFragment: RxBaseFragment(), NotesDelegateAdapter.onViewSelectedListener, MedsView {

    @Inject lateinit var medsPresenter: MedsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val meds = (meds_list.adapter as NotesAdapter).getNotes()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View? = inflater?.inflate(R.layout.fragment_meds, container, false)

        mHealthApp.medsComponent.inject(this)

        medsPresenter.setPresenterSubscriptions(subscriptions)
        medsPresenter.setView(this)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        medsPresenter.clearView()
    }

    override fun onItemSelected(item: Note) {
        medsPresenter.onNoteClicked(item)
    }

    override fun showMeds(meds: List<Note>) {
        (meds_list.adapter as NotesAdapter).addNotes(meds)
    }

    override fun showNoteDetails(note: Note) {
        var noteDialog = NoteDetailDialog()
        noteDialog.note = note
        noteDialog.show(fragmentManager, "")
    }

    override fun showAddNewNote() {
    }

  /*  fun apiCalls(){
        api.getUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user -> println(user)
                },
                        {
                            e -> println(e.message.toString())
                        })

        api.getPatients().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    patients -> println(patients)
                },
                        {
                            e -> println(e.message.toString())
                        })

        api.getPatrons().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    patrons -> println(patrons)
                },
                        {
                            e -> println(e.message.toString())
                        })
    }
    */




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