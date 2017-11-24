package plaque.mhealth.ui.user_main_slider.fragments.sitters

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_sitters.*
import kotlinx.android.synthetic.main.fragment_sitters.*
import plaque.mhealth.R
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.User
import plaque.mhealth.ui.dialogs.AddSitterDialog
import plaque.mhealth.ui.adapters.UserAdapter
import plaque.mhealth.ui.adapters.UserDelegateAdapter
import plaque.mhealth.ui.dialogs.SitterDialog
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class SittersFragment: RxBaseFragment(), UserDelegateAdapter.onViewSelectedListener, SittersView{

    @Inject lateinit var sittersPresenter: SittersPresenter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_sitters, container, false)


        mHealthApp.sittersComponent.inject(this)

        sittersPresenter.setPresenterSubscriptions(subscriptions)
        sittersPresenter.setView(this)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_sitters.setOnClickListener{ _ -> sittersPresenter.onFabClicked()}

    }

    override fun showSitters(sitters: ArrayList<User>) {
        (sitters_list.adapter as UserAdapter).addUsers(sitters)
    }

    override fun showSitterDetails(sitter: User) {
        val dialog = SitterDialog()
        dialog.user = sitter
        dialog.show(fragmentManager, "SitterDialog")
    }

    override fun showAddNewSitter() {
        val addUserDialog = AddSitterDialog()
        addUserDialog.show(fragmentManager, "")
    }

    override fun onItemSelected(user: User) {
        sittersPresenter.onSitterClicked(user)
    }

    fun addSitter(email: String){
        sittersPresenter.onSitterAdded(email)
    }

    override fun updateSitters(user: User){
        (sitters_list.adapter as UserAdapter).addUser(user)
        sittersPresenter.onSittersChanged(((sitters_list.adapter as UserAdapter).getUsers()) as ArrayList<User>)
    }

    override fun onDestroy() {
        super.onDestroy()
        sittersPresenter.clearView()
        sittersPresenter.closeRealm()
    }

    private fun initAdapter(){

        sitters_list.setHasFixedSize(true)
        sitters_list.layoutManager = LinearLayoutManager(context)

        if(sitters_list.adapter == null){
            sitters_list.adapter = UserAdapter(this)
        }
    }

    companion object {
        fun newInstance(): SittersFragment = SittersFragment()
    }
}