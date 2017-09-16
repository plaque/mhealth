package plaque.mhealth.Android.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import plaque.mhealth.R

/**
 * Created by szymon on 13.09.17.
 */
class ResultsFragment: RxBaseFragment(){

    var page: Int = 2

    companion object {
        fun newInstance(page: Int): ResultsFragment {
            var resultsFragment: ResultsFragment = ResultsFragment()
            var bundle: Bundle? = Bundle()
            bundle?.putInt("page", page)
            resultsFragment.arguments = bundle

            return resultsFragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments.getInt("page")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_results, container, false)
        return view
    }
}