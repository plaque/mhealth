package plaque.mhealth.ui.user_main_slider.fragments.results

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import plaque.mhealth.database.DataStore
import plaque.mhealth.model.Result
import plaque.mhealth.model.User
import javax.inject.Inject

/**
 * Created by szymon on 01.11.17.
 */
class ResultsPresenterImpl @Inject constructor(var dataStore: DataStore): ResultsPresenter {

    lateinit var subscriptions: CompositeDisposable
    lateinit var resultsView: ResultsView

    override fun setView(view: ResultsView) {
        resultsView = view
        getUser()
        showResults()
    }

    override fun clearView() {
        resultsView = ResultsView.EmptyResultsView()
    }

    override fun closeRealm() {
        dataStore.closeRealm()
    }

    override fun onFabClicked() {
        resultsView.showAddNewResult()
    }

    override fun onLoad(user: User) {
        dataStore.saveUser(user)
    }

    override fun onResultClicked(result: Result) {
        resultsView.showResultDetail(result)
    }

    override fun setPresenterSubscriptions(subscriptions: CompositeDisposable) {
        this.subscriptions = subscriptions
    }

    override fun onResultsChange(results: ArrayList<Result>) {
        dataStore.getUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user -> user.results = results
                    dataStore.updateNotes(user)
                },{
                    e -> println(e.message.toString())
                })
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


    private fun showResults() {
        val subscription = dataStore.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            results -> resultsView.showResults(results)
                        },
                        {
                            e -> print(e.message)
                        }
                )
        subscriptions.add(subscription)
    }
}