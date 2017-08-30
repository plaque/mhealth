package plaque.mhealth.Model

import java.time.DayOfWeek

class CyclicNote : Note {

    constructor(days: List<DayOfWeek>, hours: List<String>, content: String, title: String)
            : super(content, title) {
        this.days = days
        this.hours = hours
    }

    var days: List<DayOfWeek>? = null

    var hours: List<String>? = null


}
