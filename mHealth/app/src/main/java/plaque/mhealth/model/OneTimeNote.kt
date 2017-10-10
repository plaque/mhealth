package plaque.mhealth.model

import plaque.mhealth.commons.getFullDate
import plaque.mhealth.database.entities.OneTimeNoteEntity
import java.util.*

/**
 * Created by szymon on 30.08.17.
 */
class OneTimeNote(var date: Calendar, content: String, title: String,
                       active: Boolean) : Note(content, title, active){

    fun toOneTimeNoteEntity() = OneTimeNoteEntity(this.date.getFullDate(), this.content, this.title, this.active)
}