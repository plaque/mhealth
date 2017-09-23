package plaque.mhealth.UI.UserSlider.Fragments.Meds

import android.support.design.widget.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_meds.*
import plaque.mhealth.Database.RealmService
import plaque.mhealth.Managers.MedsManager
import plaque.mhealth.Model.Note
import plaque.mhealth.UI.Adapters.NotesAdapter
import plaque.mhealth.UI.UserSlider.RxBase
import javax.inject.Inject

/**
 * Created by szymon on 22.09.17.
 */
class MedsPresenterImpl @Inject constructor(var realmService: RealmService): MedsPresenter, RxBase {


    private var subscriptions: CompositeDisposable = CompositeDisposable()
    private val medsManager by lazy { MedsManager() }

    lateinit var medsView: MedsView

    override fun setView(view: MedsView) {
        medsView = view
        subscribe()
        showNotes()
    }

    private fun showNotes() {
        requestMeds()
    }

    override fun clearView() {
        unsubscribe()
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

    override fun subscribe(){
        if(!subscriptions.isDisposed){
            subscriptions.dispose()
        }
        subscriptions.clear()
    }

    override fun unsubscribe() {
        subscriptions.dispose()
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
}