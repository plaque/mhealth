package plaque.mhealth.model

import java.time.DayOfWeek

class CyclicNote(var days: List<DayOfWeek>, var hours: List<String>, content: String, title: String,
                      active: Boolean): Note(content, title, active)