package plaque.mhealth.model

import plaque.mhealth.ui.adapters.AdapterConstants
import plaque.mhealth.ui.adapters.ViewType

/**
 * Created by szymon on 18.08.17.
 */
abstract class Note(var content: String, var title: String,
                    var active: Boolean): ViewType {

    override fun getViewType(): Int = AdapterConstants.MEDS
}