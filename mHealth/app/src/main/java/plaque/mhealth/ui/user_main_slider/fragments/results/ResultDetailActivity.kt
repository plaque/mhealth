package plaque.mhealth.ui.user_main_slider.fragments.results

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_result.*
import plaque.graphlibtest.MyXAxisValueFormatter
import plaque.mhealth.R
import plaque.mhealth.database.DataStore
import plaque.mhealth.database.RealmService
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.Result
import plaque.mhealth.ui.dialogs.AddMeasurementDialog
import plaque.mhealth.ui.dialogs.ManageMeasurementsDialog
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by szymon on 01.11.17.
 */
class ResultDetailActivity: AppCompatActivity() {

    var result: Result? = null
    @Inject lateinit var realm: RealmService
    @Inject lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mHealthApp.resultDetailComponent.inject(this)

        getResult()

        result_name.text = result?.title

        setGraph()

        manage_measurement_button.setOnClickListener{_ -> showManageMeasurementsDialog()}
    }

    private fun showManageMeasurementsDialog() {
        val dialog = ManageMeasurementsDialog()
        dialog.show(supportFragmentManager, "CalendarDialog")
    }



    private fun setGraph() {
        val values = setXAxis()

        val entries = setEntries(values)



        if(!entries.isEmpty()){
            val dataSet = LineDataSet(entries, result?.title)
            val lineData = LineData(dataSet)
            chart.data = lineData
        }


        chart.description.isEnabled = false
        chart.axisRight.isEnabled = false
        chart.legend.isEnabled = false
        chart.invalidate()
    }

    fun refreshView(){
        setGraph()
    }


    private fun getResult() {
        userResult()

        if(result == null){
            patientResult()
        }
    }

    private fun patientResult(){
        val email = intent?.getStringExtra("email")
        val resultTitle = intent?.getStringExtra("result_title")
        val pupil = realm.getPupil(email)
        manage_measurement_button.visibility = View.GONE
        result = pupil.results?.filter { it.title == resultTitle }?.first()
    }

    private fun userResult(){
        val resultTitle = intent?.getStringExtra("result_title")
        result = realm.getUser()?.results?.filter { it.title == resultTitle }?.firstOrNull()
    }

    private fun setXAxis(): ArrayList<String>{

        val cal: Calendar = Calendar.getInstance()
        val format1 = SimpleDateFormat("dd-MM")
        val format2 = SimpleDateFormat("dd-MM-yyyy")
        cal.timeInMillis = System.currentTimeMillis()
        val valuesOnAxis = arrayListOf<String>()
        val values = arrayListOf<String>()
        cal.add(Calendar.DATE, -5)

        for(i in 1..5){
            cal.add(Calendar.DATE, 1)
            values.add(format2.format(cal.time))
        }

        cal.timeInMillis = System.currentTimeMillis()
        cal.add(Calendar.DATE, -6)
        for(i in 1..7){
            cal.add(Calendar.DATE, 1)
            valuesOnAxis.add(format1.format(cal.time))
        }

        val xAxis: XAxis = chart.xAxis
        xAxis.valueFormatter = MyXAxisValueFormatter(valuesOnAxis.toArray(arrayOf<String>()))
        xAxis.granularity = 1f
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        return values

    }

    private fun setEntries(xValues: ArrayList<String>): ArrayList<Entry>{
        val entries = arrayListOf<Entry>()

        var i = 1f

        xValues.forEach{
            val date = it
            val value = result?.results?.find { it.date == date }?.result
            if(value != null){
                entries.add(Entry(i, value))
            }
            i++
        }



        return entries
    }

    private fun updateResult(){
        val email = intent?.getStringExtra("email")

        if(email == null){
            val user = realm.getUser()
            val results = user?.results
            val index = results?.indexOf(results.find { it.title == this.result?.title })
            results!![index!!] = this.result!!
            user.results = results
            dataStore.updateResults(user)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        updateResult()
    }

}