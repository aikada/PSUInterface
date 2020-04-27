package ee.aikada.psuinterface

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ee.aikada.psuinterface.ui.profileGroups.ProfileGroupsFragment
import ee.aikada.psuinterface.ui.profiles.ProfilesListFragment


class ProfileActivity : AppCompatActivity() {
    private val TAG = ProfileActivity::class.java.simpleName
    var channelName: String? = null
    var groupName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        channelName = intent.getStringExtra("channelName")
        groupName = intent.getStringExtra("groupName")

        setContentView(R.layout.activity_profile)
        if (groupName != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProfilesListFragment.newInstance(groupName))
                .commitNow()
        } else if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProfileGroupsFragment.newInstance())
                .commitNow()
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        Log.d(TAG, "onBackPressed")
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }

    }

    fun openMainActivity() {
        Log.d(TAG, "openMainActivity")
        val intent = Intent(this, ProfileActivity::class.java)
        this.startActivity(intent)
    }
//    override fun onBackPressed() {
//        Log.d(TAG, "onBackPressed")
//        when (supportFragmentManager.findFragmentById(R.id.container)) {
//            is ProfilesFragment -> {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, ProfileGroupsFragment.newInstance())
//                    .commitNow()
//            }
//            else -> {
//                super.onBackPressed()
//            }
//        }
//    }
}
