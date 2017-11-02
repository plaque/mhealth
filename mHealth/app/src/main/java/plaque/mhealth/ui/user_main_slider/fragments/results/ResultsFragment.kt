package plaque.mhealth.ui.user_main_slider.fragments.results

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_results.*
import kotlinx.android.synthetic.main.fragment_results.*
import plaque.mhealth.R
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.Result
import plaque.mhealth.ui.adapters.ResultsAdapter
import plaque.mhealth.ui.adapters.ResultsDelegateAdapter
import plaque.mhealth.ui.dialogs.AddResultDialog
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment
import javax.inject.Inject

/**
 * Created by szymon on 13.09.17.
 */
class ResultsFragment: RxBaseFragment(), ResultsDelegateAdapter.onViewSelectedListener, ResultsView{

    @Inject lateinit var resultsPresenter: ResultsPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.fragment_results, container, false)


        mHealthApp.resultsComponent.inject(this)

        resultsPresenter.setPresenterSubscriptions(subscriptions)
        resultsPresenter.setView(this)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener{ _ -> resultsPresenter.onFabClicked()}

    }

    override fun onDestroy() {
        super.onDestroy()
        resultsPresenter.clearView()
        resultsPresenter.closeRealm()
    }

    override fun showResultDetail(result: Result) {
        val intent = Intent(context, ResultDetailActivity::class.java)
        intent.putExtra("result_title", result.title)
        startActivity(intent)
    }

    override fun showAddNewResult() {
        val addResultDialog = AddResultDialog()
        addResultDialog.show(fragmentManager, "AddResultDialog")
    }

    override fun showResults(results: ArrayList<Result>) {
        (results_list.adapter as ResultsAdapter).addResults(results)
    }

    override fun onItemSelected(result: Result) {
        resultsPresenter.onResultClicked(result)
    }

    private fun initAdapter(){

        results_list.setHasFixedSize(true)
        results_list.layoutManager = LinearLayoutManager(context)

        if(results_list.adapter == null){
            results_list.adapter = ResultsAdapter(this)
        }
    }

    companion object {
        fun newInstance(): ResultsFragment = ResultsFragment()
    }
}