package plaque.mhealth.ui.pupil.fragments.pupil_sitters

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_sitters.*
import kotlinx.android.synthetic.main.fragment_sitters.*
import plaque.mhealth.R
import plaque.mhealth.model.User
import plaque.mhealth.ui.adapters.UserAdapter
import plaque.mhealth.ui.adapters.UserDelegateAdapter
import plaque.mhealth.ui.dialogs.SitterDialog
import plaque.mhealth.ui.pupil.PupilDetailActivity
import plaque.mhealth.ui.user_main_slider.fragments.RxBaseFragment

/**
 * Created by szymon on 24.11.17.
 */
class PupilSittersFragment: RxBaseFragment(), UserDelegateAdapter.onViewSelectedListener {

    lateinit var pupilActivity: PupilDetailActivity

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_sitters, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        pupilActivity = activity as PupilDetailActivity
        showSitters()

        fab_sitters.hide()
    }

    override fun onItemSelected(user: User) {
        val dialog = SitterDialog()
        dialog.user = user
        dialog.show(fragmentManager, "SitterDialog")
    }

    private fun initAdapter(){

        sitters_list.setHasFixedSize(true)
        sitters_list.layoutManager = LinearLayoutManager(context)

        if(sitters_list.adapter == null){
            sitters_list.adapter = UserAdapter(this)
        }
    }

    fun showSitters(){
        pupilActivity.dataStore.getSitters((activity as PupilDetailActivity).user.email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sitters -> (sitters_list.adapter as UserAdapter).addUsers(sitters)
                    pupilActivity.dataStore.saveSitters((activity as PupilDetailActivity).user.email, sitters)
                },{
                    e -> println(e.message.toString())
                })

    }

    companion object {
        fun newInstance(): PupilSittersFragment = PupilSittersFragment()
    }
}