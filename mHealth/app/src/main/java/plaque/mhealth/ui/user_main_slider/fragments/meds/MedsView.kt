package plaque.mhealth.ui.user_main_slider.fragments.meds

import plaque.mhealth.model.CyclicNote

/**
 * Created by szymon on 22.09.17.
 */
interface MedsView {

    fun showNotes(notes: List<CyclicNote>)
    fun showNoteDetails(note: CyclicNote, position: Int)
    fun showAddNewNote()

    class EmptyMedsView: MedsView{
        override fun showNoteDetails(note: CyclicNote, position: Int) {

        }

        override fun showNotes(notes: List<CyclicNote>) {

        }

        override fun showAddNewNote() {

        }

    }
}