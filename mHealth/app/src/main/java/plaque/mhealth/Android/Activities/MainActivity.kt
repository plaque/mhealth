package plaque.mhealth.Android.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
