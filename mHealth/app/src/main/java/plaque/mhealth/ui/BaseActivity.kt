package plaque.mhealth.ui

import android.support.v7.app.AppCompatActivity

/**
 * Created by szymon on 21.09.17.
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onDestroy() {
        closeRealm()
        super.onDestroy()
    }

    abstract fun closeRealm(): Unit

}