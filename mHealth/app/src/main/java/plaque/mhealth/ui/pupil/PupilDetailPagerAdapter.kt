package plaque.mhealth.ui.pupil

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import plaque.mhealth.R
import plaque.mhealth.ui.pupil.fragments.pupil_results.PupilsResultsFragment
import plaque.mhealth.ui.pupil.fragments.pupils_meds.PupilsMedsFragment

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
            0 -> return PupilsMedsFragment.newInstance()
            1 -> return PupilsResultsFragment.newInstance()
            else -> return PupilsMedsFragment.newInstance()
        }
    }

    override fun getCount(): Int = NUM_ITEMS

}