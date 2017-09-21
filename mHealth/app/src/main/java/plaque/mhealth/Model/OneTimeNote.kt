package plaque.mhealth.Model

import java.util.*

/**
 * Created by szymon on 30.08.17.
 */
class OneTimeNote(var date: Calendar, content: String, title: String,
                       active: Boolean) : Note(content, title, active){

}