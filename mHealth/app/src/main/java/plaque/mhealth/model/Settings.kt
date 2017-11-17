package plaque.mhealth.model

import plaque.mhealth.database.entities.SettingsEntity

/**
 * Created by szymon on 17.11.17.
 */
data class Settings(var notifications: Boolean, var emails: Boolean, var sms: Boolean) {

    fun toSettingsEntity() = SettingsEntity(notifications, emails, sms)
}