package plaque.mhealth.ui.pupil

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pupil_details.*
import plaque.mhealth.R
import plaque.mhealth.database.DataStore
import plaque.mhealth.database.RealmService
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.User
import javax.inject.Inject

/**
 * Created by szymon on 15.10.17.
 */
class PupilDetailActivity : AppCompatActivity() {

    lateinit var user: User
    @Inject lateinit var realm: RealmService
    @Inject lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pupil_details)

        mHealthApp.pupilDetailComponent.inject(this)

        val email = intent.getStringExtra("email")
        user = realm.getPupil(email)
        pupil_user_name.text = "${user.name} ${user.surname}"
        initPager()

    }

    fun initPager(){
        pupil_detail_vp.adapter = PupilDetailPagerAdapter(supportFragmentManager)
        pd_tab_pager.setupWithViewPager(pupil_detail_vp)
        setUpText(pd_tab_pager, pupil_detail_vp.adapter as PupilDetailPagerAdapter)
    }

    fun setUpText(tabLayout: TabLayout, adapter: PupilDetailPagerAdapter){
        for (index in adapter.imageResId.indices){
            tabLayout.getTabAt(index)?.setText(adapter.imageResId[index])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.closeRealm()
    }
}