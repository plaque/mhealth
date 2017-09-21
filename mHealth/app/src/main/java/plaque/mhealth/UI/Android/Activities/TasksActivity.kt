package plaque.mhealth.UI.Android.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_tasks.*
import plaque.mhealth.UI.Android.Fragments.MedsFragment
import plaque.mhealth.UI.Android.Fragments.PupilsFragment
import plaque.mhealth.UI.Android.Fragments.ResultsFragment
import plaque.mhealth.UI.Android.Fragments.SittersFragment

import plaque.mhealth.R

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        vpPager.adapter = TasksPagerAdapter(supportFragmentManager)
        tab_pager.setupWithViewPager(vpPager)
        setUpIcons(tab_pager, vpPager.adapter as TasksPagerAdapter)

        val position = intent.getIntExtra("position", 0)
        vpPager.currentItem = position

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
                "Results",
                "Patrons",
                "Pupils"
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

}

