package plaque.mhealth.ui.user_main_slider.fragments.results

import plaque.mhealth.model.Result

/**
 * Created by szymon on 01.11.17.
 */
interface ResultsView {

    fun showResultDetail(result: Result)
    fun showAddNewResult()
    fun showResults(results: ArrayList<Result>)

    class EmptyResultsView(): ResultsView{
        override fun showResultDetail(result: Result) {
        }

        override fun showAddNewResult() {
        }

        override fun showResults(results: ArrayList<Result>) {
        }

    }
}