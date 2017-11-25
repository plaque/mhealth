package plaque.mhealth.ui.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import kotlinx.android.synthetic.main.calendar_dialog.*
import plaque.mhealth.R
import plaque.mhealth.model.DateResultPair
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultDetailActivity
import java.text.SimpleDateFormat
import kotlin.collections.HashSet

/**
 * Created by szymon on 25.11.17.
 */
class ManageMeasurementsDialog: DialogFragment(), OnDateSelectedListener {

    var dates = hashSetOf<CalendarDay>()
    lateinit var date: CalendarDay

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(R.layout.calendar_dialog, container)


        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dates = parseDates()

        apply {
            hide_all()
            calendarView.setOnDateChangedListener(this)
            calendarView.selectionColor = resources.getColor(R.color.colorPrimary)
            calendarView.addDecorator(EventDecorator(resources.getColor(R.color.colorPrimaryDark), dates))
            add_measurement.setOnClickListener{_ -> addMeasurement()}
            delete_measurement.setOnClickListener{_ -> deleteMeasurement()}
        }


    }

    fun refreshCalendar(){
        dates = parseDates()
        calendarView.addDecorator(EventDecorator(resources.getColor(R.color.colorPrimaryDark), dates))
        calendarView.invalidateDecorators()
    }

    fun parseDates(): HashSet<CalendarDay>{
        val dates = hashSetOf<CalendarDay>()
        val format1 = SimpleDateFormat("dd-MM-yyyy")
        (activity as ResultDetailActivity).result?.results?.forEach {
            dates.add(CalendarDay.from(format1.parse(it.date)))
        }

        return dates
    }



    private fun deleteMeasurement() {
        val format1 = SimpleDateFormat("dd-MM-yyyy")
        val date = format1.format(this.date.calendar.time)
        (activity as ResultDetailActivity).result?.results?.remove((activity as ResultDetailActivity).result?.results?.find{ it.date == date })
        (activity as ResultDetailActivity).refreshView()
        calendarView.removeDecorator(EventDecorator(resources.getColor(R.color.colorPrimaryDark), dates))
        calendarView.invalidateDecorators()
        show_add()
        hide_delete()
        refreshCalendar()
    }

    private fun addMeasurement() {
        val format1 = SimpleDateFormat("dd-MM-yyyy")
        val date = format1.format(date.calendar.time)
        var value_measure: Float
        try {
            value_measure = measurement_value.text.toString().toFloat()
        }catch (e: NumberFormatException){
            value_measure = 0f
        }

        show_delete()
        hide_add()
        (activity as ResultDetailActivity).result?.results?.add(DateResultPair(date, value_measure))
        (activity as ResultDetailActivity).refreshView()
        value.text = (activity as ResultDetailActivity).result?.results?.find { it.date == format1.format(this.date.calendar.time) }?.result.toString()
        refreshCalendar()
    }

    override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        val format1 = SimpleDateFormat("dd-MM-yyyy")
        this.date = date
        if(date in dates){
            value.text = (activity as ResultDetailActivity).result?.results?.find { it.date == format1.format(date.calendar.time) }?.result.toString()
            show_delete()
            hide_add()
        }else{
            show_add()
            hide_delete()
        }

    }

    fun hide_all(){
        hide_add_button()
        hide_add_value()
        hide_delete_button()
        hide_value()
    }

    fun hide_add(){
        hide_add_value()
        hide_add_button()
    }

    fun show_add(){
        show_add_button()
        show_add_value()
    }

    fun hide_delete(){
        hide_delete_button()
        hide_value()
    }

    fun show_delete(){
        show_delete_button()
        show_value()
    }

    fun show_delete_button(){
        delete_measurement.visibility = View.VISIBLE
    }

    fun hide_delete_button(){
        delete_measurement.visibility = View.GONE
    }

    fun show_value(){
        value.visibility = View.VISIBLE
    }

    fun hide_value(){
        value.visibility = View.GONE
    }

    fun show_add_value(){
        measurement_value.visibility = View.VISIBLE
    }

    fun hide_add_value(){
        measurement_value.visibility = View.GONE
    }

    fun show_add_button(){
        add_measurement.visibility = View.VISIBLE
    }

    fun hide_add_button(){
        add_measurement.visibility = View.GONE
    }
}