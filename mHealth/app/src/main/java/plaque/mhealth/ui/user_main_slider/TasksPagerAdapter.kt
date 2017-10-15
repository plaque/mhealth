package plaque.mhealth.ui.user_main_slider

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import plaque.mhealth.R
import plaque.mhealth.ui.user_main_slider.fragments.meds.MedsFragment
import plaque.mhealth.ui.user_main_slider.fragments.pupils.PupilsFragment
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultsFragment
import plaque.mhealth.ui.user_main_slider.fragments.sitters.SittersFragment

/**
 * Created by szymon on 22.09.17.
 */
class TasksPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

    val NUM_ITEMS = 4

    val imageResId: Array<Int> = arrayOf(
            R.string.meds_schedule,
            R.string.health_results,
            R.string.sitters,
            R.string.pupils
    )


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return MedsFragment.newInstance()
            1 -> return ResultsFragment.newInstance()
            2 -> return SittersFragment.newInstance()
            3 -> return PupilsFragment.newInstance()
            else -> return MedsFragment.newInstance()
        }
    }

    override fun getCount(): Int = NUM_ITEMS

}