package plaque.mhealth.ui.user_slider.fragments.meds

import io.realm.Realm
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.User
import plaque.mhealth.ui.BasePresenter
import plaque.mhealth.ui.RxBase

/**
 * Created by szymon on 22.09.17.
 */
interface MedsPresenter: BasePresenter<MedsView>, RxBase {

    fun onLoad(user: User)
    fun onNoteClicked(note: CyclicNote)
    fun onFabClicked()

}