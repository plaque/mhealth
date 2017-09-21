package plaque.mhealth.UI.Android.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import plaque.mhealth.R

/**
 * Created by szymon on 13.09.17.
 */
class SittersFragment: RxBaseFragment(){

    companion object {
        fun newInstance(): SittersFragment = SittersFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_sitters, container, false)
        return view
    }
}