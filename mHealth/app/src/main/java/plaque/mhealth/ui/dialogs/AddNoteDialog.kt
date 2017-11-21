package plaque.mhealth.ui.dialogs

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TimePicker
import com.cunoraz.tagview.TagView
import kotlinx.android.synthetic.main.add_note.*
import kotlinx.android.synthetic.main.content_meds.*
import org.jetbrains.anko.forEachChild
import plaque.mhealth.R
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.HourMinutePair
import plaque.mhealth.ui.adapters.NotesAdapter
import plaque.mhealth.ui.user_main_slider.fragments.meds.MedsFragment

/**
 * Created by szymon on 23.10.17.
 */
class AddNoteDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    val time = arrayListOf<HourMinutePair>()

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val hourMinute = HourMinutePair(p1, p2)
        if(hourMinute !in time){
            time.add(hourMinute)

            val tags = arrayListOf<com.cunoraz.tagview.Tag>()
            time.forEach{
                val tag = com.cunoraz.tagview.Tag(it.toString())
                tag.isDeletable = true
                tag.layoutColor = resources.getColor(R.color.colorPrimary)
                tag.layoutColorPress = resources.getColor(R.color.colorPrimaryDark)
                tags.add(tag)
            }
            dialog.findViewById<TagView>(R.id.tag_group).addTags(tags)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(R.layout.add_note, container)


        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apply {
            new_add_hour.setOnClickListener { _ -> showTimePicker() }
            save_note.setOnClickListener { _ -> saveNote() }
            tag_group.setOnTagDeleteListener { tagView, tag, i -> time.removeAt(i)
                                                                tagView.remove(i)}
        }
    }

    private fun showTimePicker(){
        val fragment: TimePickerFragment = TimePickerFragment()
        fragment.listener = this
        fragment.show(fragmentManager, "timePicker")
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
        val hours = time

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