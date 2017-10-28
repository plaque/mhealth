package plaque.mhealth.ui.dialogs

import plaque.mhealth.ui.user_main_slider.fragments.sitters.SittersFragment

/**
 * Created by szymon on 26.10.17.
 */
class AddSitterDialog: AddUserDialog() {

    override fun addUser(email: String) {
        fragmentManager.fragments.forEach {
            if(it is SittersFragment){
                it.addSitter(email)
            }
        }
        this.dismiss()
    }
}