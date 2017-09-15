package plaque.mhealth.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_tasks.*
import plaque.mhealth.Fragments.MedsFragment
import plaque.mhealth.Fragments.PupilsFragment
import plaque.mhealth.Fragments.ResultsFragment
import plaque.mhealth.Fragments.SittersFragment

import plaque.mhealth.R

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        vpPager.adapter = TasksPagerAdapter(supportFragmentManager)
        tab_pager.setupWithViewPager(vpPager)

        setUpIcons(tab_pager, vpPager.adapter as TasksPagerAdapter)

    }

    fun setUpIcons(tabLayout: TabLayout, adapter: TasksPagerAdapter){
        for (index in adapter.imageResId.indices){
            tabLayout.getTabAt(index)?.setText(adapter.imageResId[index])
        }
    }
    class TasksPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

        val NUM_ITEMS = 4

        val imageResId: Array<String> = arrayOf(
            "Meds",
            "Patients",
            "Pupils",
            "Results")


        override fun getItem(position: Int): Fragment {
            when(position){
                0 -> return MedsFragment.newInstance(0)
                1 -> return PupilsFragment.newInstance(1)
                2 -> return ResultsFragment.newInstance(2)
                3 -> return SittersFragment.newInstance(3)
                else -> return MedsFragment.newInstance(0)
            }
        }

        override fun getCount(): Int {
            return NUM_ITEMS;
        }

    }

}

