package plaque.mhealth.ui.user_main_slider.fragments.results

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_result.*
import plaque.graphlibtest.MyXAxisValueFormatter
import plaque.mhealth.R
import plaque.mhealth.database.RealmService
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.Result
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by szymon on 01.11.17.
 */
class ResultDetailActivity: AppCompatActivity() {

    private var result: Result? = null
    @Inject lateinit var realm: RealmService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mHealthApp.resultDetailComponent.inject(this)

        getResult()

        result_name.text = result?.title

        val last = result?.results?.last()

        results.text = ""

        result?.results?.forEach {
            if(last == it){
                results.append("${it.result}")
            }else{
                results.append("${it.result}, ")
            }
        }

        setGraph()

    }

    private fun setGraph() {


        setXAxis()

        val dataSet = LineDataSet(setEntries(), result?.title)
        val lineData = LineData(dataSet)

        chart.data = lineData
        chart.invalidate()
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
        result = pupil?.results?.filter { it.title == resultTitle }?.first()
    }

    private fun userResult(){
        val resultTitle = intent?.getStringExtra("result_title")
        result = realm.getUser()?.results?.filter { it.title == resultTitle }?.firstOrNull()
    }

    private fun setXAxis(){

        val cal: Calendar = Calendar.getInstance()
        val format1: SimpleDateFormat = SimpleDateFormat("dd-MM")
        cal.timeInMillis = System.currentTimeMillis()
        val values = arrayListOf<String>()
        cal.add(Calendar.DATE, -3)

        for(i in 1..5){
            cal.add(Calendar.DATE, 1)
            values.add(format1.format(cal.time))
        }

        val xAxis: XAxis = chart.xAxis
        xAxis.valueFormatter = MyXAxisValueFormatter(values.toArray(arrayOf<String>()))
        xAxis.granularity = 1f

    }

    private fun setEntries(): ArrayList<Entry>{
        val entries = arrayListOf<Entry>()

        var i = 1f

        result?.results?.forEach {
            entries.add(Entry(i, it.result))
            i++
        }


        return entries
    }
}