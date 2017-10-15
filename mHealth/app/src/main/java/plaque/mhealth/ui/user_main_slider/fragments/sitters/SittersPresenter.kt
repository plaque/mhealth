package plaque.mhealth.ui.user_main_slider.fragments.sitters

import plaque.mhealth.model.User
import plaque.mhealth.ui.BasePresenter
import plaque.mhealth.ui.RxBase

/**
 * Created by szymon on 15.10.17.
 */
interface SittersPresenter:BasePresenter<SittersView>, RxBase{
    fun onLoad(user: User)
    fun onSitterClicked(user: User)
    fun onFabClicked()
}