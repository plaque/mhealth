package plaque.mhealth.Model

import java.util.*

/**
 * Created by szymon on 30.08.17.
 */
class OneTimeNote : Note {

    constructor(date: Calendar, content: String, title: String) : super(content, title){
        this.date = date
    }

    var date: Calendar? = null
}