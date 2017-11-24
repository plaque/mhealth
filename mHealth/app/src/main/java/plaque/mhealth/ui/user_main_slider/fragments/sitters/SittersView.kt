package plaque.mhealth.ui.user_main_slider.fragments.sitters

import plaque.mhealth.model.User

/**
 * Created by szymon on 15.10.17.
 */
interface SittersView {

    fun showSitters(sitters: ArrayList<User>)
    fun showSitterDetails(sitter: User)
    fun showAddNewSitter()
    fun updateSitters(user: User)

    class EmptySittersView: SittersView {
        override fun showSitterDetails(sitter: User) {

        }

        override fun showSitters(sitters: ArrayList<User>) {

        }

        override fun showAddNewSitter() {

        }

        override fun updateSitters(user: User){

        }

    }
}