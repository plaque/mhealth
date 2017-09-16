package plaque.mhealth.Model

import plaque.mhealth.Adapters.AdapterConstants
import plaque.mhealth.Adapters.ViewType


/**
 * Created by szymon on 18.08.17.
 */
abstract class Note(open var content: String, open var title: String,
                    open var active: Boolean): ViewType {

    override fun getViewType(): Int = AdapterConstants.MEDS
}