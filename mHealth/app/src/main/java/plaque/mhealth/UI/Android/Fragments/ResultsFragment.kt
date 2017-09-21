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


    companion object {
        fun newInstance(): ResultsFragment = ResultsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_results, container, false)
        return view
    }
}