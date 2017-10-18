package plaque.mhealth.ui.pupil.fragments.pupils_meds

import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.User
import plaque.mhealth.ui.adapters.NotesDelegateAdapter
import plaque.mhealth.ui.adapters.UserDelegateAdapter
import plaque.mhealth.ui.pupil.PupilDetailActivity
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment

/**
 * Created by szymon on 15.10.17.
 */
class PupilsMedsFragment: RxBaseFragment(), NotesDelegateAdapter.onViewSelectedListener {

    override fun onItemSelected(note: CyclicNote) {
    }

    companion object {
        fun newInstance(): PupilsMedsFragment = PupilsMedsFragment()
    }
}