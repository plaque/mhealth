package plaque.mhealth.UI.Android.Login

import plaque.mhealth.Model.Login
import plaque.mhealth.UI.Android.BasePresenter

/**
 * Created by szymon on 21.09.17.
 */
interface LoginPresenter: BasePresenter<LoginView> {
    fun onLoginClick(login: Login)
}