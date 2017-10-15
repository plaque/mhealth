package plaque.mhealth.ui.user_main_slider.fragments.pupils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import plaque.mhealth.database.DataStore
import plaque.mhealth.model.User
import javax.inject.Inject

/**
 * Created by szymon on 15.10.17.
 */

class PupilsPresenterImpl @Inject constructor(var dataStore: DataStore): PupilsPresenter {

    lateinit var subscriptions: CompositeDisposable
    lateinit var pupilsView: PupilsView

    override fun setView(view: PupilsView) {
        pupilsView = view
        getUser()
        showPupils()
    }

    override fun clearView() {
        pupilsView = PupilsView.EmptyPupilsView()
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

    override fun onPupilClicked(user: User) {
        pupilsView.showPupilDetails(user)
    }

    override fun onFabClicked() {
        pupilsView.showAddNewPupil()
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

    private fun showPupils(){
        val subscription = dataStore.getPupils()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pupils -> pupilsView.showPupils(pupils)
                    dataStore.savePupils(pupils)
                },{
                    e -> println(e.message)
                })
        subscriptions.add(subscription)
    }


}