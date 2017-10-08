package plaque.mhealth.database.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by szymon on 08.10.17.
 */
abstract class NoteEntity(var content: String, var title: String,
                          var active: Boolean,
                          @PrimaryKey @Transient val id: String? = UUID.randomUUID().toString()): RealmObject()