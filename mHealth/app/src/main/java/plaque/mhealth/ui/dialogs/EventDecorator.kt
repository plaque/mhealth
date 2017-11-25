package plaque.mhealth.ui.dialogs

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

/**
 * Created by szymon on 25.11.17.
 */
class EventDecorator(var color: Int, var dates: HashSet<CalendarDay>): DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean = dates.contains(day)

    override fun decorate(view: DayViewFacade?) = view?.addSpan(DotSpan(10f, color) )!!
}