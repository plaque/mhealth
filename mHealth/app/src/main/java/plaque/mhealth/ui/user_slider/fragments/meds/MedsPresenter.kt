package plaque.mhealth.ui.user_slider.fragments.meds

import plaque.mhealth.model.Note
import plaque.mhealth.ui.BasePresenter
import plaque.mhealth.ui.RxBase

/**
 * Created by szymon on 22.09.17.
 */
interface MedsPresenter: BasePresenter<MedsView>, RxBase {
    fun onNoteClicked(note: Note)
    fun onFabClicked()

}