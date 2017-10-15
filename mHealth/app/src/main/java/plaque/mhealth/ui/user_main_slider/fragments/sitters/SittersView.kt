package plaque.mhealth.ui.user_main_slider.fragments.sitters

import plaque.mhealth.model.User

/**
 * Created by szymon on 15.10.17.
 */
interface SittersView {

    fun showSitters(sitters: ArrayList<User>)
    fun showSitterDetails(sitters: User)
    fun showAddNewSitter()

    class EmptySittersView: SittersView {
        override fun showSitterDetails(sitters: User) {

        }

        override fun showSitters(sitters: ArrayList<User>) {

        }

        override fun showAddNewSitter() {

        }

    }
}