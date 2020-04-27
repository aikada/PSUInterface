package ee.aikada.psuinterface.ui.profiles.componentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ee.aikada.psuinterface.R

private const val ARG_TITLE = "param1"
private const val ARG_VALUE = "param2"
private const val ARG_UNIT = "param3"

class SetParameterFragment : Fragment() {
    private var title: String? = null
    private var value: Float? = null
    private var unit: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            value = it.getFloat(ARG_VALUE)
            unit = it.getString(ARG_UNIT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_set_parameter, container, false)
        v.findViewById<TextView>(R.id.fragment_set_parameter_name).text = title
        v.findViewById<TextView>(R.id.fragment_set_parameter_value).setText(value.toString())
        v.findViewById<TextView>(R.id.fragment_set_parameter_unit).text = unit
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, value: Float, unit: String) =
            SetParameterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putFloat(ARG_VALUE, value)
                    putString(ARG_UNIT, unit)
                }
            }
    }
}
