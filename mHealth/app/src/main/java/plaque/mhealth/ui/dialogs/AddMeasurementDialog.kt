package plaque.mhealth.ui.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.add_measurement.*
import kotlinx.android.synthetic.main.add_result.*
import plaque.mhealth.R
import plaque.mhealth.model.DateResultPair
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultDetailActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by szymon on 15.11.17.
 */
class AddMeasurementDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(R.layout.add_measurement, container)
        dialog.requestWindowFeature(DialogFragment.STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_measurement_button.setOnClickListener{_ -> addMeasurement()}
    }

    private fun addMeasurement() {
        val format1: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = format1.format(Date(datePicker2.year, datePicker2.month, datePicker2.dayOfMonth))
        var value: Float
        try {
            value = measurement_text.text.toString().toFloat()
        }catch (e: NumberFormatException){
            value = 0f
        }
        (activity as ResultDetailActivity).result?.results?.add(DateResultPair(date, value))

        this.dismiss()
    }

}