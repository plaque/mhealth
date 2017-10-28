package plaque.mhealth.ui.dialogs

import android.app.Dialog

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.TimePicker

/**
 * Created by szymon on 28.10.17.
 */
class TimePickerFragment: DialogFragment() {

    lateinit var listener: TimePickerDialog.OnTimeSetListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            TimePickerDialog(context, listener, 12, 0, true )
}