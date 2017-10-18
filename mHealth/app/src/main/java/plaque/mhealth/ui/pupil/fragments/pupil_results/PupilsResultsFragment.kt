package plaque.mhealth.ui.pupil.fragments.pupil_results

import plaque.mhealth.model.User
import plaque.mhealth.ui.adapters.UserDelegateAdapter
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment
import plaque.mhealth.ui.user_main_slider.fragments.pupils.PupilsFragment
import javax.inject.Inject

/**
 * Created by szymon on 15.10.17.
 */
class PupilsResultsFragment: RxBaseFragment(), UserDelegateAdapter.onViewSelectedListener{


    override fun onItemSelected(pupil: User) {

    }

    companion object {
        fun newInstance(): PupilsResultsFragment = PupilsResultsFragment()
    }
}