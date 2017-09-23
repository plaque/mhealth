package plaque.mhealth.UI.UserSlider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_tasks.*

import plaque.mhealth.R

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        initPager()
        setPagerPosition()

    }

    fun initPager(){
        vpPager.adapter = TasksPagerAdapter(supportFragmentManager)
        tab_pager.setupWithViewPager(vpPager)
        setUpIcons(tab_pager, vpPager.adapter as TasksPagerAdapter)
    }

    fun setPagerPosition(){
        vpPager.currentItem = intent.getIntExtra("position", 0)
    }

    fun setUpIcons(tabLayout: TabLayout, adapter: TasksPagerAdapter){
        for (index in adapter.imageResId.indices){
            tabLayout.getTabAt(index)?.text = adapter.imageResId[index]
        }
    }


}

