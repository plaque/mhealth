package plaque.mhealth.ui.activities

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.fall_detected_activity.*
import plaque.mhealth.R

/**
 * Created by szymon on 07.10.17.
 */
class FallDetectedActivity: Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fall_detected_activity)

        dismiss_button.setOnClickListener{ _ -> finish()}
    }
}