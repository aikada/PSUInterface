package ee.aikada.psuinterface.ui.profiles.profileTypeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.helpers.FragmentManagerHelper

class CVFragment(val profile: ProfileDTO? = null) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile_type_container, container, false)
        addFragmentsForProfile(profile!!)

        return v
    }

    private fun addFragmentsForProfile(profile: ProfileDTO) {
        val fragmentManagerHelper =
            FragmentManagerHelper(
                childFragmentManager,
                R.id.linearLayout_fragment_profile_type_container
            )

        fragmentManagerHelper.addSetParameterFragment("Voltage", profile.voltage, "V")
        fragmentManagerHelper.addSetParameterFragment("Current limit", profile.currentLimit, "A")
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
        fun newInstance() = CVFragment()
        fun newInstance(profile: ProfileDTO?) =
            CVFragment(profile)
    }
}

