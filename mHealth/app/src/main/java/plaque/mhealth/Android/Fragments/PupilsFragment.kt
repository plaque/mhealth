package plaque.mhealth.Android.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import plaque.mhealth.R

/**
 * Created by szymon on 13.09.17.
 */
class PupilsFragment: RxBaseFragment(){

    var page: Int = 1

    companion object {
        fun newInstance(page: Int): PupilsFragment {
            var pupilsFragment: PupilsFragment = PupilsFragment()
            var bundle: Bundle? = Bundle()
            bundle?.putInt("page", page)
            pupilsFragment.arguments = bundle

            return pupilsFragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments.getInt("page")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_pupils, container, false)
        return view
    }
}