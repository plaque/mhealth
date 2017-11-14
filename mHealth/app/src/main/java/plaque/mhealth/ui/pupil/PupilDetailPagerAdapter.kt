package plaque.mhealth.ui.pupil

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import plaque.mhealth.R
import plaque.mhealth.ui.pupil.fragments.pupil_results.PupilResultsFragment
import plaque.mhealth.ui.pupil.fragments.pupil_meds.PupilMedsFragment

/**
 * Created by szymon on 15.10.17.
 */
class PupilDetailPagerAdapter (fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

    val NUM_ITEMS = 2

    val imageResId: Array<Int> = arrayOf(
            R.string.meds_schedule,
            R.string.health_results
    )


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return PupilMedsFragment.newInstance()
            1 -> return PupilResultsFragment.newInstance()
            else -> return PupilMedsFragment.newInstance()
        }
    }

    override fun getCount(): Int = NUM_ITEMS

}