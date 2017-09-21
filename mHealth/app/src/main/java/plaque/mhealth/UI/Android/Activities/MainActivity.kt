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

        meds_button.setOnClickListener { _ ->  startActivityWithExtra(0) }

        results_button.setOnClickListener { _ -> startActivityWithExtra(1) }

        sitters_button.setOnClickListener { _ -> startActivityWithExtra(2) }

        pupils_button.setOnClickListener { _ -> startActivityWithExtra(3) }

    }

    fun startActivityWithExtra(position: Int){
        intent = Intent(this, TasksActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

}
