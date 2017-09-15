package plaque.mhealth.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import plaque.mhealth.Fragments.MedsFragment
import plaque.mhealth.Fragments.PupilsFragment
import plaque.mhealth.Fragments.ResultsFragment
import plaque.mhealth.Fragments.SittersFragment
import plaque.mhealth.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        meds_button.setOnClickListener { _ -> startActivity(Intent(this, TasksActivity::class.java)) }

        results_button.setOnClickListener { _ -> startActivity(Intent(this, TasksActivity::class.java)) }

        sitters_button.setOnClickListener { _ -> startActivity(Intent(this, TasksActivity::class.java)) }

        pupils_button.setOnClickListener { _ -> startActivity(Intent(this, TasksActivity::class.java)) }
    }


}
