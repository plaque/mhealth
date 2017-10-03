package plaque.mhealth.UI.UserSlider.Fragments

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by szymon on 14.09.17.
 */
open class RxBaseFragment: Fragment(){
    var subscriptions = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if(!subscriptions.isDisposed){
            subscriptions.dispose()
        }
        subscriptions.clear()
    }
}