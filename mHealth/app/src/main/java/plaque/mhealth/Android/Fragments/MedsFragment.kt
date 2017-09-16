package plaque.mhealth.Android.Fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_meds.*
import plaque.mhealth.Adapters.NotesAdapter
import plaque.mhealth.Managers.MedsManager
import plaque.mhealth.R
import plaque.mhealth.Retrofit.UserRestAPI
import plaque.mhealth.mHealthApp
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class MedsFragment: RxBaseFragment(){

    @Inject lateinit var api: UserRestAPI

    private val medsManager by lazy { MedsManager() }
    var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments.getInt("page")

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
                            retrivedMeds -> (meds_list.adapter as NotesAdapter).addMeds(retrivedMeds)
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

    private fun initAdapter(){
        if(meds_list.adapter == null){
            meds_list.adapter = NotesAdapter()
        }
    }

    companion object {
        fun newInstance(page: Int): MedsFragment{
            var medsFragment: MedsFragment = MedsFragment()
            var bundle: Bundle? = Bundle()
            bundle?.putInt("page", page)
            medsFragment.arguments = bundle

            return medsFragment
        }
    }
}