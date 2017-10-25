package plaque.mhealth.ui.notes

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import kotlinx.android.synthetic.main.add_note.*
import kotlinx.android.synthetic.main.content_meds.*
import org.jetbrains.anko.forEachChild
import plaque.mhealth.R
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.ui.adapters.NotesAdapter
import plaque.mhealth.ui.user_main_slider.fragments.meds.MedsFragment

/**
 * Created by szymon on 23.10.17.
 */
class NewNoteDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(R.layout.add_note, container)


        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apply {
            save_note.setOnClickListener { _ -> saveNote() }
        }
    }

    private fun saveNote(){

        val title = note_title.text.toString()
        val content = note_content.text.toString()
        val days = arrayListOf<Int>()
        add_days.forEachChild {
            if((it as CheckBox).isChecked){
                days.add(it.tag.toString().toInt())
            }
        }
        val hours = arrayListOf(note_title.text.toString())

        val adapter: NotesAdapter
        var medsFragment: MedsFragment? = null

        fragmentManager.fragments.forEach {
            if(it is MedsFragment) {
                medsFragment = it
            }
        }

        adapter = (medsFragment?.meds_list?.adapter as NotesAdapter)

        adapter.addNote(CyclicNote(days, hours, content, title, true))

        medsFragment?.onNotesChange()


        this.dismiss()
    }
}