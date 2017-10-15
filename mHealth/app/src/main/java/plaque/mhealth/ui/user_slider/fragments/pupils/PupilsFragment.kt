package plaque.mhealth.ui.user_slider.fragments.pupils

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.content_pupils.*
import kotlinx.android.synthetic.main.fragment_meds.*
import kotlinx.android.synthetic.main.fragment_pupils.*
import plaque.mhealth.R
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.User
import plaque.mhealth.ui.adapters.NotesAdapter
import plaque.mhealth.ui.adapters.PeopleDelegateAdapter
import plaque.mhealth.ui.adapters.PupilsAdapter
import plaque.mhealth.ui.user_slider.fragments.RxBaseFragment
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class PupilsFragment: RxBaseFragment(), PeopleDelegateAdapter.onViewSelectedListener, PupilsView{

    @Inject lateinit var pupilsPresenter: PupilsPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_pupils, container, false)

        mHealthApp.pupilsComponent.inject(this)

        pupilsPresenter.setPresenterSubscriptions(subscriptions)
        pupilsPresenter.setView(this)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_pupils.setOnClickListener{ _ -> pupilsPresenter.onFabClicked()}

    }

    override fun onDestroy() {
        super.onDestroy()
        pupilsPresenter.clearView()
        pupilsPresenter.closeRealm()
    }

    override fun showPupils(pupils: ArrayList<User>) {
        (pupils_list.adapter as PupilsAdapter).addPupils(pupils)
    }

    override fun showPupilDetails(user: User) {
    }

    override fun showAddNewPupil() {

    }

    override fun onItemSelected(pupil: User) {
        pupilsPresenter.onPupilClicked(pupil)
    }

    private fun initAdapter(){

        pupils_list.setHasFixedSize(true)
        pupils_list.layoutManager = LinearLayoutManager(context)

        if(pupils_list.adapter == null){
            pupils_list.adapter = PupilsAdapter(this)
        }
    }

    companion object {
        fun newInstance(): PupilsFragment = PupilsFragment()
    }
}