package plaque.mhealth.ui.user_slider.fragments.sitters

import io.reactivex.disposables.CompositeDisposable
import plaque.mhealth.database.DataStore
import javax.inject.Inject

/**
 * Created by szymon on 15.10.17.
 */
class SittersPresenterImpl @Inject constructor(var dataStore: DataStore): SittersPresenter {

    lateinit var subscriptions: CompositeDisposable
    lateinit var sittersView: SittersView

    override fun setView(view: SittersView) {
        sittersView = view
    }

    override fun clearView() {

    }

    override fun setPresenterSubscriptions(subscriptions: CompositeDisposable) {

    }

    override fun closeRealm() {
        dataStore.closeRealm()
    }
}