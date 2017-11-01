package plaque.mhealth.ui.user_main_slider.fragments.results

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import plaque.mhealth.R

/**
 * Created by szymon on 01.11.17.
 */
class ResultDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_result)
    }
}