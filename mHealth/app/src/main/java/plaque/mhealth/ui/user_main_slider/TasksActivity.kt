package plaque.mhealth.ui.user_main_slider

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
        setUpText(tab_pager, vpPager.adapter as TasksPagerAdapter)
    }

    fun setPagerPosition(){
        vpPager.currentItem = intent.getIntExtra("position", 0)
    }

    fun setUpText(tabLayout: TabLayout, adapter: TasksPagerAdapter){
        for (index in adapter.imageResId.indices){
            tabLayout.getTabAt(index)?.setText(adapter.imageResId[index])
        }
    }


}

