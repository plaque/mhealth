package plaque.mhealth.ui.pupil.fragments.pupil_results

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_results.*
import kotlinx.android.synthetic.main.fragment_results.*
import plaque.mhealth.R
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.Result
import plaque.mhealth.model.User
import plaque.mhealth.ui.adapters.NotesDelegateAdapter
import plaque.mhealth.ui.adapters.ResultsAdapter
import plaque.mhealth.ui.adapters.ResultsDelegateAdapter
import plaque.mhealth.ui.adapters.UserDelegateAdapter
import plaque.mhealth.ui.pupil.PupilDetailActivity
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment
import plaque.mhealth.ui.user_main_slider.fragments.pupils.PupilsFragment
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultDetailActivity
import javax.inject.Inject

/**
 * Created by szymon on 15.10.17.
 */
class PupilsResultsFragment: RxBaseFragment(), ResultsDelegateAdapter.onViewSelectedListener{


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_results, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        (results_list.adapter as ResultsAdapter).addResults((activity as PupilDetailActivity).user.results!!.toList())
        fab.hide()
    }

    override fun onItemSelected(result: Result) {
        val intent = Intent(context, ResultDetailActivity::class.java)
        intent.putExtra("result_title", result.title)
        startActivity(intent)
    }

    private fun initAdapter(){

        results_list.setHasFixedSize(true)
        results_list.layoutManager = LinearLayoutManager(context)

        if(results_list.adapter == null){
            results_list.adapter = ResultsAdapter(this)
        }
    }

    companion object {
        fun newInstance(): PupilsResultsFragment = PupilsResultsFragment()
    }
}