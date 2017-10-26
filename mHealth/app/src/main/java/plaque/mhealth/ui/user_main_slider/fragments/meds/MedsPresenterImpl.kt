package plaque.mhealth.ui.user_main_slider.fragments.meds

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import plaque.mhealth.database.DataStore
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.User
import javax.inject.Inject

/**
 * Created by szymon on 22.09.17.
 */
class MedsPresenterImpl @Inject constructor(var dataStore: DataStore): MedsPresenter{

    lateinit var subscriptions: CompositeDisposable
    lateinit var medsView: MedsView

    override fun setView(view: MedsView) {
        medsView = view
        getUser()
        showNotes()
    }

    override fun onLoad(user: User) {
        dataStore.saveUser(user)
    }

    override fun clearView() {
        medsView = MedsView.EmptyMedsView()
    }

    override fun closeRealm() {
        dataStore.closeRealm()
    }

    override fun onNoteClicked(note: CyclicNote, position: Int) {
        medsView.showNoteDetails(note, position)
    }

    override fun onFabClicked() {
        medsView.showAddNewNote()
    }

    override fun setPresenterSubscriptions(subscriptions: CompositeDisposable){
        this.subscriptions = subscriptions
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

    private fun showNotes() {
        val subscription = dataStore.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            notes -> medsView.showNotes(notes)
                        },
                        {
                            e -> print(e.message)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun onNotesChange(notes: ArrayList<CyclicNote>) {
        dataStore.getUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user -> user.notes = notes
                    dataStore.updateNotes(user)
                },{
                    e -> println(e.message.toString())
                })
    }
}