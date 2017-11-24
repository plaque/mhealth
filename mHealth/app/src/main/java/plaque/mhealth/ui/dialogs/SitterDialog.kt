package plaque.mhealth.ui.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.sitter_dialog.*
import org.jetbrains.anko.support.v4.email
import org.jetbrains.anko.support.v4.makeCall
import plaque.mhealth.R
import plaque.mhealth.model.User

/**
 * Created by szymon on 24.11.17.
 */
class SitterDialog: DialogFragment() {

    lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View? = inflater?.inflate(R.layout.sitter_dialog, container)


        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apply {
            phone_button.setOnClickListener{_ -> makeCall(user.phoneNumber.toString())}
            message_button.setOnClickListener{_ -> email(user.email)}
        }
    }

}