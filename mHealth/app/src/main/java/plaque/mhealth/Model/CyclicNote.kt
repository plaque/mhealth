package plaque.mhealth.Model

import java.time.DayOfWeek

data class CyclicNote(var days: List<DayOfWeek>, var hours: List<String>,
                      override var content: String, override var title: String, override var active: Boolean)
    : Note(content, title, active) {

}
