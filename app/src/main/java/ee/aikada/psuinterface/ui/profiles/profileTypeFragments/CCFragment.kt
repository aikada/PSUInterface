package ee.aikada.psuinterface.ui.profiles.profileTypeFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.helpers.FragmentManagerHelper
import ee.aikada.psuinterface.ui.profiles.ProfileAddEditViewModel

class CCFragment(val profile: ProfileDTO? = null) : Fragment() {
    val TAG = CCFragment::class.java.simpleName

    private lateinit var viewModel: ProfileAddEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile_type_container, container, false)
        viewModel = ViewModelProvider(this).get(ProfileAddEditViewModel::class.java)
        addFragmentsForProfile(profile!!)

        return v
    }

    private fun addFragmentsForProfile(profile: ProfileDTO) {
        val fragmentManagerHelper =
            FragmentManagerHelper(
                childFragmentManager,
                R.id.linearLayout_fragment_profile_type_container
            )

        fragmentManagerHelper.addSetParameterFragment("Voltage limit", profile.voltageLimit, "V")
        fragmentManagerHelper.addSetParameterFragment("Current", profile.current, "A")
        fragmentManagerHelper.addSetTimePropertyFragment("Duration", profile.duration)
        fragmentManagerHelper.addSetParameterFragment(
            "Emulated internal resistance",
            profile.resistance,
            "Ω"
        )
        fragmentManagerHelper.addSetBooleanFragment(
            "Disable output when limit exceeded",
            profile.latchOff
        )
    }


    companion object {
        fun newInstance() = CCFragment()
        fun newInstance(profile: ProfileDTO?) =
            CCFragment(profile)
    }
}

