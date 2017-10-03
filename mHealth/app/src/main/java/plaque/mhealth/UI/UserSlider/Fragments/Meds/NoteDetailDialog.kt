package plaque.mhealth.UI.UserSlider.Fragments.Meds

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.cyclic_note.*
import kotlinx.android.synthetic.main.one_time_note.*
import plaque.mhealth.Commons.getFullDate
import plaque.mhealth.Model.CyclicNote
import plaque.mhealth.Model.Note
import plaque.mhealth.Model.OneTimeNote
import plaque.mhealth.R

/**
 * Created by szymon on 23.09.17.
 */
class NoteDetailDialog(): DialogFragment() {

    lateinit var note: Note

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View = View(context)

        when(note){
            is CyclicNote -> view = inflater?.inflate(R.layout.cyclic_note, container)!!
            is OneTimeNote -> view = inflater?.inflate(R.layout.one_time_note, container)!!
        }

        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(note){
            is CyclicNote -> apply { title.text = note.title
                content.text = note.content
                days.text = (note as CyclicNote).days.toString()
                hours.text = (note as CyclicNote).hours.toString()
                edit.setOnClickListener{ _ -> changeToEditLayout() }
            }
            is OneTimeNote -> apply { ot_title.text = note.title
                ot_content.text = note.content
                date.text = (note as OneTimeNote).date.getFullDate()
                ot_edit.setOnClickListener{ _ -> changeToEditLayout() }
            }
        }
    }

    private fun changeToEditLayout(){

        when(note){

            is CyclicNote -> {  val newView = layoutInflater?.inflate(R.layout.edit_cyclic_note, null)

                                newView?.apply {
                                    findViewById<EditText>(R.id.edit_title)?.setText(note.title)
                                    findViewById<TextInputEditText>(R.id.edit_content)?.setText(note.content)
                                    findViewById<TextView>(R.id.edit_days)?.text = (note as CyclicNote).days.toString()
                                    findViewById<TextView>(R.id.edit_hours)?.text = (note as CyclicNote).hours.toString()
                                }
                                this.dialog.setContentView(R.layout.edit_cyclic_note)}

            is OneTimeNote -> { val newView = layoutInflater?.inflate(R.layout.edit_one_time_note, null)
                                newView?.apply { findViewById<EditText>(R.id.edit_ot_title)?.setText(note.title)
                                        findViewById<TextInputEditText>(R.id.edit_ot_content)?.setText(note.content)
                                        findViewById<TextView>(R.id.edit_date)?.text = (note as OneTimeNote).date.getFullDate()}
                                this.dialog.setContentView(newView)}
        }
    }

}