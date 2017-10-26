package plaque.mhealth.ui.user_main_slider.fragments.pupils

import io.reactivex.Observable
import plaque.mhealth.model.User
import plaque.mhealth.ui.BasePresenter
import plaque.mhealth.ui.RxBase

/**
 * Created by szymon on 15.10.17.
 */
interface PupilsPresenter: BasePresenter<PupilsView>, RxBase {
    fun onLoad(user: User)
    fun onPupilClicked(user: User)
    fun onFabClicked()
    fun onPupilAdded(email: String)
    fun onPupilsChanged(pupils: ArrayList<User>)
}