package plaque.mhealth.Model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.UI.Adapters.AdapterConstants
import plaque.mhealth.UI.Adapters.ViewType
import java.util.*


/**
 * Created by szymon on 18.08.17.
 */
abstract class Note(var content: String, var title: String,
                    var active: Boolean, @PrimaryKey @Transient val id: String? = UUID.randomUUID().toString()): RealmObject(), ViewType {

    override fun getViewType(): Int = AdapterConstants.MEDS
}