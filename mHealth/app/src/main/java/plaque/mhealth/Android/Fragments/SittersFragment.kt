package plaque.mhealth.Android.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import plaque.mhealth.R

/**
 * Created by szymon on 13.09.17.
 */
class SittersFragment: RxBaseFragment(){

    var page: Int = 3

    companion object {
        fun newInstance(page: Int): SittersFragment {
            var sittersFragment: SittersFragment = SittersFragment()
            var bundle: Bundle? = Bundle()
            bundle?.putInt("page", page)
            sittersFragment.arguments = bundle

            return sittersFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments.getInt("page")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_sitters, container, false)
        return view
    }
}