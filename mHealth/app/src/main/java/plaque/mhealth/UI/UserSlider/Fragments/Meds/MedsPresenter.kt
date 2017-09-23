package plaque.mhealth.UI.UserSlider.Fragments.Meds

import plaque.mhealth.Model.Note
import plaque.mhealth.UI.BasePresenter

/**
 * Created by szymon on 22.09.17.
 */
interface MedsPresenter: BasePresenter<MedsView> {
    fun onNoteClicked(note: Note)
    fun onFabClicked()

}