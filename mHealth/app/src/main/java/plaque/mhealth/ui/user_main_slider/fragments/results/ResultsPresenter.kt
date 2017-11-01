package plaque.mhealth.ui.user_main_slider.fragments.results

import plaque.mhealth.model.Result

/**
 * Created by szymon on 01.11.17.
 */
interface ResultsPresenter {

    fun onFabClicked()
    fun onResultClicked(result: Result)

}