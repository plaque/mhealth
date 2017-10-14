package plaque.mhealth.ui.user_slider.fragments.meds

import plaque.mhealth.model.CyclicNote

/**
 * Created by szymon on 22.09.17.
 */
interface MedsView {

    fun showMeds(meds: List<CyclicNote>)
    fun showNoteDetails(note: CyclicNote)
    fun showAddNewNote()

    class EmptyMedsView: MedsView{
        override fun showNoteDetails(note: CyclicNote) {

        }

        override fun showMeds(meds: List<CyclicNote>) {

        }

        override fun showAddNewNote() {

        }

    }
}