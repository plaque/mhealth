package plaque.mhealth.ui

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by szymon on 23.09.17.
 */
interface RxBase {
    fun setPresenterSubscriptions(subscriptions: CompositeDisposable)
}