package plaque.mhealth.ui.login

import plaque.mhealth.model.Login
import plaque.mhealth.ui.BasePresenter

/**
 * Created by szymon on 21.09.17.
 */
interface LoginPresenter: BasePresenter<LoginView> {
    fun onLoginClick(login: Login)
}