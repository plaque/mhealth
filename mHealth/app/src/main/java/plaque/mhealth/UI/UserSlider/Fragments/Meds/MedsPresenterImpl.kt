package plaque.mhealth.UI.UserSlider.Fragments.Meds

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import plaque.mhealth.Database.RealmService
import plaque.mhealth.Managers.MedsManager
import plaque.mhealth.Model.Note
import javax.inject.Inject

/**
 * Created by szymon on 22.09.17.
 */
class MedsPresenterImpl @Inject constructor(var realmService: RealmService): MedsPresenter{

    private val medsManager by lazy { MedsManager() }
    lateinit var subscriptions: CompositeDisposable
    lateinit var medsView: MedsView

    override fun setView(view: MedsView) {
        medsView = view
        showNotes()
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

    override fun onNoteClicked(note: Note) {
        medsView.showNoteDetails(note)
    }

    override fun onFabClicked() {

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