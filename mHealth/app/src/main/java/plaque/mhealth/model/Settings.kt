package plaque.mhealth.model

import plaque.mhealth.database.entities.SettingsEntity

/**
 * Created by szymon on 17.11.17.
 */
data class Settings(var push: Boolean, var emails: Boolean, var sms: Boolean,
                    var fallMonitoring: Boolean ) {

    fun toSettingsEntity() = SettingsEntity(push, emails, sms, fallMonitoring)
}