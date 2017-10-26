package plaque.mhealth.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.add_user.*
import plaque.mhealth.R
import plaque.mhealth.ui.user_main_slider.fragments.pupils.PupilsFragment
import plaque.mhealth.ui.user_main_slider.fragments.sitters.SittersFragment

/**
 * Created by szymon on 23.10.17.
 */
open class AddUserDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(R.layout.add_user, container)
        dialog.requestWindowFeature(STYLE_NO_TITLE)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_user_submit.setOnClickListener{_ -> addUser(add_user_email.text.toString())}
    }

    open fun addUser(email: String){

    }




}