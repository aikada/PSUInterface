package ee.aikada.psuinterface.ui.profiles.profileTypeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.helpers.FragmentManagerHelper

class GraphFragment(profile: ProfileDTO? = null) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile_type_container, container, false)
        val fragmentManagerHelper =
            FragmentManagerHelper(
                childFragmentManager,
                R.id.linearLayout_fragment_profile_type_container
            );

        fragmentManagerHelper.addSetParameterFragment("Voltage", 0.0F, "V")
        fragmentManagerHelper.addSetParameterFragment("Current", 0.0F, "A")
        fragmentManagerHelper.addSetTimePropertyFragment("Time", "00:00:00")
        fragmentManagerHelper.addSetParameterFragment("Emulated internal resistance", 0.0F, "Ω")
        fragmentManagerHelper.addSetBooleanFragment("Disable output when limit exceeded", false)

        return v
    }

    companion object {
        fun newInstance() = GraphFragment()
        fun newInstance(profile: ProfileDTO) =
            GraphFragment(profile)
    }
}

