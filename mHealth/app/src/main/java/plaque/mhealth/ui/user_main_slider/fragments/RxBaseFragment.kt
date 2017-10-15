package plaque.mhealth.ui.user_main_slider.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by szymon on 14.09.17.
 */
open class RxBaseFragment: Fragment(){
    var subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscriptions = CompositeDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!subscriptions.isDisposed){
            subscriptions.dispose()
        }
        subscriptions.clear()
    }
}