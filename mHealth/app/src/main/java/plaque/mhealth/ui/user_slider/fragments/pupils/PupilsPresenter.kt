package plaque.mhealth.ui.user_slider.fragments.pupils

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
}