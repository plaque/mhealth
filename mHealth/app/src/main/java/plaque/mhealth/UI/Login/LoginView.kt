package plaque.mhealth.UI.Login

/**
 * Created by szymon on 21.09.17.
 */
interface LoginView {

    fun showMainActivity()
    fun showErrorMessage(message: String)

    class EmptyLoginView: LoginView {
        override fun showErrorMessage(message: String) {
        }

        override fun showMainActivity() {
        }
    }
}