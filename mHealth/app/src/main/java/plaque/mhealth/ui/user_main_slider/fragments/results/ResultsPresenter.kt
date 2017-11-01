package plaque.mhealth.ui.user_main_slider.fragments.results

import plaque.mhealth.model.Result
import plaque.mhealth.model.User
import plaque.mhealth.ui.BasePresenter
import plaque.mhealth.ui.RxBase

/**
 * Created by szymon on 01.11.17.
 */
interface ResultsPresenter: BasePresenter<ResultsView>, RxBase {

    fun onLoad(user: User)
    fun onFabClicked()
    fun onResultClicked(result: Result)

}