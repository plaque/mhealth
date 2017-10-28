package plaque.mhealth.ui.dialogs

import plaque.mhealth.ui.user_main_slider.fragments.pupils.PupilsFragment

/**
 * Created by szymon on 26.10.17.
 */
class AddPupilDialog: AddUserDialog() {

    override fun addUser(email: String){
        fragmentManager.fragments.forEach {
            if(it is PupilsFragment){
                it.addPupil(email)
            }
        }
        this.dismiss()
    }
}