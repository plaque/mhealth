package plaque.mhealth.UI.UserSlider.Fragments.Meds

import plaque.mhealth.Model.Note

/**
 * Created by szymon on 22.09.17.
 */
interface MedsView {

    fun showMeds(meds: List<Note>)
    fun showNoteDetails(note: Note)
    fun showAddNewNote()

    class EmptyMedsView: MedsView{
        override fun showNoteDetails(note: Note) {

        }

        override fun showMeds(meds: List<Note>) {

        }

        override fun showAddNewNote() {

        }

    }
}