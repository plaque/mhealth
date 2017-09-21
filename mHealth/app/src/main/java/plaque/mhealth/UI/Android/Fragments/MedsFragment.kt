package plaque.mhealth.UI.Android.Fragments

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.cyclic_note.*
import kotlinx.android.synthetic.main.one_time_note.*
import plaque.mhealth.UI.Android.Adapters.NotesDelegateAdapter
import plaque.mhealth.UI.Android.Adapters.NotesAdapter
import plaque.mhealth.Commons.getFullDate
import plaque.mhealth.Managers.MedsManager
import plaque.mhealth.Model.CyclicNote
import plaque.mhealth.Model.Note
import plaque.mhealth.Model.OneTimeNote
import plaque.mhealth.R
import plaque.mhealth.Retrofit.UserRestAPI
import plaque.mhealth.mHealthApp
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class MedsFragment: RxBaseFragment(), NotesDelegateAdapter.onViewSelectedListener {

    @Inject lateinit var api: UserRestAPI

    private val medsManager by lazy { MedsManager() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        meds_list.setHasFixedSize(true)
        meds_list.layoutManager = LinearLayoutManager(context)
        initAdapter()

        mHealthApp.medsComponent.inject(this)

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

        if (savedInstanceState == null) {
            requestMeds()
        }

    }

    private fun requestMeds(){
        val subscription = medsManager.getMeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            retrivedMeds -> (meds_list.adapter as NotesAdapter).addNotes(retrivedMeds)
                        },
                        {
                            e -> Snackbar.make(meds_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (meds_list.adapter as NotesAdapter).getNotes()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View? = inflater?.inflate(R.layout.fragment_meds, container, false)

        return view
    }

    override fun onItemSelected(item: Note) {
        var noteDialog = Dialog(context)
        if(item is CyclicNote){
            noteDialog.setContentView(R.layout.cyclic_note)
            noteDialog.apply {
                title?.text = item.title
                content?.text = item.content
                days?.text = item.days.toString()
                hours?.text = item.hours.toString()
            }
            noteDialog.show()
        }
        if(item is OneTimeNote){
            noteDialog.setContentView(R.layout.one_time_note)
            noteDialog.apply {
                ot_title?.text = item.title
                ot_content?.text = item.content
                date?.text = item.date.getFullDate()
            }
            noteDialog.show()
        }
    }

    private fun initAdapter(){
        if(meds_list.adapter == null){
            meds_list.adapter = NotesAdapter(this)
        }
    }

    companion object {
        fun newInstance(): MedsFragment = MedsFragment()
    }
}