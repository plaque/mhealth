package plaque.mhealth.ui.user_slider.fragments.meds

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import plaque.mhealth.database.RealmService
import plaque.mhealth.managers.MedsManager
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.User
import plaque.mhealth.retrofit.UserRestAPI
import javax.inject.Inject

/**
 * Created by szymon on 22.09.17.
 */
class MedsPresenterImpl @Inject constructor(var realmService: RealmService,
                                            var api: UserRestAPI): MedsPresenter{


    private val medsManager by lazy { MedsManager() }
    lateinit var subscriptions: CompositeDisposable
    lateinit var medsView: MedsView


    private fun realmTest() {

        api.getUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user -> onLoad( user)
                },
                        {
                            e -> println(e.message.toString())
                        })


    }


    override fun setView(view: MedsView) {
        medsView = view
        showNotes()
        realmTest()
    }

    override fun onLoad(user: User) {
        realmService.addUser(user)
    }

    private fun showNotes() {
        requestMeds()
    }

    override fun clearView() {
        medsView = MedsView.EmptyMedsView()
    }

    override fun closeRealm() {
        realmService.closeRealm()
    }

    override fun onNoteClicked(note: CyclicNote) {
        medsView.showNoteDetails(note)
    }

    override fun onFabClicked() {
        realmService.getUser()
    }

    private fun requestMeds(){
        val subscription = medsManager.getMeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            retrievedMeds -> medsView.showMeds(retrievedMeds)
                        },
                        {
                            e ->
                        }
                )
        subscriptions.add(subscription)
    }

    override fun setPresenterSubscriptions(subscriptions: CompositeDisposable){
        this.subscriptions = subscriptions
    }

}