package plaque.mhealth.ui.notes

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.content_meds.*
import kotlinx.android.synthetic.main.cyclic_note.*
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.R
import plaque.mhealth.ui.adapters.NotesAdapter
import plaque.mhealth.ui.user_main_slider.fragments.meds.MedsFragment

/**
 * Created by szymon on 23.09.17.
 */
class NoteDetailDialog: DialogFragment() {

    lateinit var note: CyclicNote
    var position = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View? = inflater?.inflate(R.layout.cyclic_note, container)


        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        apply {
            title.text = note.title
            content.text = note.content
            days.text = note.days.toString()
            hours.text = note.hours.toString()
            edit.setOnClickListener { _ -> changeToEditLayout() }
        }

    }

    fun hideEditButton(){
        edit.visibility = View.INVISIBLE
    }

    private fun changeToEditLayout(){

        val newView = layoutInflater?.inflate(R.layout.edit_cyclic_note, null)

        newView?.apply {
            findViewById<EditText>(R.id.edit_title)?.setText(note.title)
            findViewById<TextInputEditText>(R.id.edit_content)?.setText(note.content)
            findViewById<TextView>(R.id.edit_days)?.text = note.days.toString()
            findViewById<TextView>(R.id.edit_hours)?.text = note.hours.toString()
            findViewById<TextView>(R.id.submit_button)?.setOnClickListener{_ -> saveChanges()}
        }

        this.dialog.setContentView(newView)


    }

    private fun saveChanges(){

        note.title = this.dialog.findViewById<EditText>(R.id.edit_title).text.toString()
        note.content = this.dialog.findViewById<EditText>(R.id.edit_content).text.toString()
        note.days = arrayListOf(this.dialog.findViewById<TextView>(R.id.edit_days).text.toString())
        note.hours = arrayListOf(this.dialog.findViewById<TextView>(R.id.edit_hours).text.toString())

        val adapter = (fragmentManager.fragments[0] as MedsFragment).meds_list.adapter
        (adapter as NotesAdapter).updateItem(note, position)

        this.dismiss()
    }
}