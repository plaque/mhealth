package plaque.mhealth.UI.Login

import plaque.mhealth.Model.Login
import plaque.mhealth.UI.BasePresenter

/**
 * Created by szymon on 21.09.17.
 */
interface LoginPresenter: BasePresenter<LoginView> {
    fun onLoginClick(login: Login)
}