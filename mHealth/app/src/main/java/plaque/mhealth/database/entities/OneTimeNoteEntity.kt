package plaque.mhealth.database.entities

import java.util.*

/**
 * Created by szymon on 08.10.17.
 */
class OneTimeNoteEntity(var date: Calendar, content: String, title: String,
                        active: Boolean) : NoteEntity(content, title, active){

}