package plaque.mhealth.ui.user_slider.fragments.meds

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.cyclic_note.*
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.R

/**
 * Created by szymon on 23.09.17.
 */
class NoteDetailDialog(): DialogFragment() {

    lateinit var note: CyclicNote

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

    private fun changeToEditLayout(){

        val newView = layoutInflater?.inflate(R.layout.edit_cyclic_note, null)

        newView?.apply {
            findViewById<EditText>(R.id.edit_title)?.setText(note.title)
            findViewById<TextInputEditText>(R.id.edit_content)?.setText(note.content)
            findViewById<TextView>(R.id.edit_days)?.text = note.days.toString()
            findViewById<TextView>(R.id.edit_hours)?.text = note.hours.toString()
        }

        this.dialog.setContentView(R.layout.edit_cyclic_note)

    }

}