package plaque.mhealth.database.entities

import java.time.DayOfWeek

/**
 * Created by szymon on 08.10.17.
 */
class CyclicNoteEntity(var days: List<DayOfWeek>, var hours: List<String>, content: String, title: String,
                       active: Boolean): NoteEntity(content, title, active)