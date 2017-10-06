package plaque.mhealth.UI.UserSlider

import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.res.TypedArrayUtils.getString
import plaque.mhealth.R
import plaque.mhealth.UI.UserSlider.Fragments.Meds.MedsFragment
import plaque.mhealth.UI.UserSlider.Fragments.Pupils.PupilsFragment
import plaque.mhealth.UI.UserSlider.Fragments.ResultsFragment
import plaque.mhealth.UI.UserSlider.Fragments.Sitters.SittersFragment

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