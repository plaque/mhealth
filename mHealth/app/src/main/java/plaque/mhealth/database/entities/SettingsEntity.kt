package plaque.mhealth.database.entities

import io.realm.RealmObject
import plaque.mhealth.model.Settings

/**
 * Created by szymon on 17.11.17.
 */
open class SettingsEntity(var push: Boolean = true, var emails: Boolean = true,
                          var sms: Boolean = true, var fallMonitoring: Boolean = true): RealmObject() {

    fun toSettings() = Settings(this.push, this.emails, this.sms, this.fallMonitoring)
}