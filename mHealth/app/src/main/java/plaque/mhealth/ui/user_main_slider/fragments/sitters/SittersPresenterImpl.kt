package plaque.mhealth.ui.user_main_slider.fragments.sitters

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import plaque.mhealth.database.DataStore
import plaque.mhealth.model.User
import javax.inject.Inject

/**
 * Created by szymon on 15.10.17.
 */
class SittersPresenterImpl @Inject constructor(var dataStore: DataStore): SittersPresenter {

    lateinit var subscriptions: CompositeDisposable
    lateinit var sittersView: SittersView

    override fun setView(view: SittersView) {
        sittersView = view
        getUser()
        showSitters()
    }

    override fun clearView() {
        sittersView = SittersView.EmptySittersView()
    }

    override fun setPresenterSubscriptions(subscriptions: CompositeDisposable) {
        this.subscriptions = subscriptions
    }

    override fun closeRealm() {
        dataStore.closeRealm()
    }

    override fun onLoad(user: User) {
        dataStore.saveUser(user)
    }

    override fun onSitterClicked(user: User) {
        sittersView.showSitterDetails(user)
    }

    override fun onFabClicked() {
        sittersView.showAddNewSitter()
    }

    override fun onSitterAdded(email: String){
        dataStore.addSitter(email).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user -> sittersView.updateSitters(user)
                },{
                    e -> println(e.message.toString())
                })
    }

    override fun onSittersChanged(sitters: ArrayList<User>) {
        dataStore.saveSitters(sitters)
    }

    private fun getUser() {
        dataStore.getUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user -> onLoad(user)
                },
                        {
                            e -> println(e.message.toString())
                        })
    }

    private fun showSitters(){
        val subscription = dataStore.getSitters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sitters -> sittersView.showSitters(sitters)
                    if(sitters.size != 0){
                        dataStore.saveSitters(sitters)
                    }
                },{
                    e -> println(e.message)
                })
        subscriptions.add(subscription)
    }
}