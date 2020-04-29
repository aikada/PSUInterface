package ee.aikada.psuinterface.ui.profiles.componentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import ee.aikada.psuinterface.R

private const val ARG_TITLE = "param1"
private const val ARG_BOOLEAN = "param2"

class SetBooleanFragment : Fragment() {
    private var title: String? = null
    private var bool: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            bool = it.getBoolean(ARG_BOOLEAN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_set_boolean, container, false)
        val switch: Switch = v.findViewById(R.id.fragment_set_boolean_switch)

        switch.text = title
        switch.isChecked = bool == true

        return v
    }

    companion object {
        fun newInstance(title: String, bool: Boolean) =
            SetBooleanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putBoolean(ARG_BOOLEAN, bool)
                }
            }
    }
}
