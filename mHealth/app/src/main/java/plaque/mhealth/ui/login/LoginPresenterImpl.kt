package plaque.mhealth.ui.login

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import plaque.mhealth.database.RealmService
import plaque.mhealth.model.Login
import plaque.mhealth.retrofit.LoginRestAPI
import plaque.mhealth.retrofit.TokenInterceptor
import javax.inject.Inject

/**
 * Created by szymon on 21.09.17.
 */

class LoginPresenterImpl @Inject constructor(var api: LoginRestAPI,
                                             var realmService: RealmService,
                                             var loginInterceptor: TokenInterceptor) : LoginPresenter {

    var loginView: LoginView = LoginView.EmptyLoginView()

    override fun setView(view: LoginView) {
        loginView = view
    }

    override fun clearView() {
        loginView = LoginView.EmptyLoginView()
    }

    override fun closeRealm() {
        realmService.closeRealm()
    }

    override fun onLoginClick(login: Login?) {
        if(login == null){
            loginView.showMainActivity()
        }else {
            api.login(login)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { response ->
                                manageResponse(response)
                                realmService.saveToken(loginInterceptor.sessionToken ?: "")
                            },
                            {
                                loginView.showErrorMessage("Check your internet connection. Server is not responding.")
                            }
                    )
        }
    }

    private fun manageResponse(response: retrofit2.Response<ResponseBody>): Unit{
        when(response.code()){
            401 -> loginView.showErrorMessage("Bad credentials. Check your login and password.")
            200 -> loginView.showMainActivity()
            500, 501, 502, 503, 504 -> loginView.showErrorMessage("Server internal error.")
        }
    }




}