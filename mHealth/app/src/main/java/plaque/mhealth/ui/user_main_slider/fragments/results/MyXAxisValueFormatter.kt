package plaque.graphlibtest

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter



/**
 * Created by szymon on 01.11.17.
 */
class MyXAxisValueFormatter(private val mValues: Array<String>) : IAxisValueFormatter {

    /** this is only needed if numbers are returned, else return 0  */
    val decimalDigits: Int
        get() = 0

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        // "value" represents the position of the label on the axis (x or y)
        if(value < 0f || value > mValues.size.toFloat() ){
            return ""
        }
        return mValues[value.toInt()]
    }
}