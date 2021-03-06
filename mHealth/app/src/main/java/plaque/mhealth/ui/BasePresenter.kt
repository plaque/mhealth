package plaque.mhealth.ui

/**
 * Created by szymon on 21.09.17.
 */
interface BasePresenter<T> {
    fun setView(view: T): Unit
    fun clearView(): Unit
    fun closeRealm(): Unit
}