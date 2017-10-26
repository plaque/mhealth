package plaque.mhealth.ui.user_main_slider.fragments.pupils

import plaque.mhealth.model.User


/**
 * Created by szymon on 15.10.17.
 */
interface PupilsView {

    fun showPupils(pupils: ArrayList<User>)
    fun showPupilDetails(user: User)
    fun showAddNewPupil()
    fun updatePupils(user: User)

    class EmptyPupilsView: PupilsView{
        override fun updatePupils(user: User) {

        }

        override fun showPupilDetails(user: User) {
        }

        override fun showAddNewPupil() {
        }

        override fun showPupils(pupils: ArrayList<User>) {

        }
    }

}