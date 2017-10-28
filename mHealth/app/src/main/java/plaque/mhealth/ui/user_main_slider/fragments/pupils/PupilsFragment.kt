package plaque.mhealth.ui.user_main_slider.fragments.pupils

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_pupils.*
import kotlinx.android.synthetic.main.fragment_pupils.*
import plaque.mhealth.R
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.User
import plaque.mhealth.ui.dialogs.AddPupilDialog
import plaque.mhealth.ui.adapters.UserDelegateAdapter
import plaque.mhealth.ui.adapters.UserAdapter
import plaque.mhealth.ui.pupil.PupilDetailActivity
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class PupilsFragment: RxBaseFragment(), UserDelegateAdapter.onViewSelectedListener, PupilsView{

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
        (pupils_list.adapter as UserAdapter).addUsers(pupils)
    }

    override fun showPupilDetails(user: User) {
        val intent = Intent(context, PupilDetailActivity::class.java)
        intent.putExtra("email", user.email)
        startActivity(intent)
    }

    override fun showAddNewPupil() {
        val addUserDialog = AddPupilDialog()
        addUserDialog.show(fragmentManager, "")
    }

    fun addPupil(email: String){
        pupilsPresenter.onPupilAdded(email)
    }

    override fun updatePupils(user: User) {
        (pupils_list.adapter as UserAdapter).addUser(user)
        pupilsPresenter.onPupilsChanged(((pupils_list.adapter as UserAdapter).getUsers()) as ArrayList<User>)
    }

    override fun onItemSelected(user: User) {
        pupilsPresenter.onPupilClicked(user )
    }

    private fun initAdapter(){

        pupils_list.setHasFixedSize(true)
        pupils_list.layoutManager = LinearLayoutManager(context)

        if(pupils_list.adapter == null){
            pupils_list.adapter = UserAdapter(this)
        }
    }

    companion object {
        fun newInstance(): PupilsFragment = PupilsFragment()
    }
}