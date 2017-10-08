package plaque.mhealth.model

import java.util.*

/**
 * Created by szymon on 30.08.17.
 */
class OneTimeNote(var date: Calendar, content: String, title: String,
                       active: Boolean) : Note(content, title, active)