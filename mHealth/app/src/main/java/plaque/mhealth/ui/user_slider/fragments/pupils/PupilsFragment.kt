package plaque.mhealth.ui.user_slider.fragments.pupils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import plaque.mhealth.R
import plaque.mhealth.ui.user_slider.fragments.RxBaseFragment

/**
 * Created by szymon on 13.09.17.
 */
class PupilsFragment: RxBaseFragment(){



    companion object {
        fun newInstance(): PupilsFragment = PupilsFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_pupils, container, false)
        return view
    }
}