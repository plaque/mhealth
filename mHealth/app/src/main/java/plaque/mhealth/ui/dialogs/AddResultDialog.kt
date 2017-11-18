package plaque.mhealth.ui.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.add_result.*
import kotlinx.android.synthetic.main.content_results.*
import plaque.mhealth.R
import plaque.mhealth.model.DateResultPair
import plaque.mhealth.model.Result
import plaque.mhealth.ui.adapters.ResultsAdapter
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultsFragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by szymon on 01.11.17.
 */
class AddResultDialog: DialogFragment(){

    var measurements = arrayListOf<DateResultPair>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(R.layout.add_result, container)
        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit_results.setOnClickListener{_ -> addResult()}
        add_measurement_button_ar.setOnClickListener { _ -> addMeasurement() }
    }

    private fun addMeasurement() {

        val format1: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val cal = Calendar.getInstance()
        cal.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
        val date = format1.format(cal.time)
        var value: Float
        try {
            value = measurement.text.toString().toFloat()
        }catch (e: NumberFormatException){
            value = 0f
        }
        measurements.add(DateResultPair(date, value))
        measurements_text.text = measurements.toString()

    }


    private fun addResult() {

        val result: Result = Result(result_title.text.toString() , measurements)

        val adapter: ResultsAdapter
        var resultsFragment: ResultsFragment? = null

        fragmentManager.fragments.forEach {
            if(it is ResultsFragment) {
                resultsFragment = it
            }
        }

        adapter = (resultsFragment?.results_list?.adapter as ResultsAdapter)

        adapter.addResult(result)

        resultsFragment?.onResultsChange()
        this.dismiss()
    }
}