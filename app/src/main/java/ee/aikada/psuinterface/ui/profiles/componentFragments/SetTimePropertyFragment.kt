package ee.aikada.psuinterface.ui.profiles.componentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ee.aikada.psuinterface.R

private const val ARG_TITLE = "param1"
private const val ARG_TIME = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SetTimePropertyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetTimePropertyFragment : Fragment() {
    private var title: String? = null
    private var time: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            time = it.getString(ARG_TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_set_time_property, container, false)
        v.findViewById<TextView>(R.id.fragment_set_time_property_name).text = title
        v.findViewById<TextView>(R.id.fragment_set_time_property_value).text = time
        return v
    }

    companion object {
        fun newInstance(title: String, time: String) =
            SetTimePropertyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_TIME, time)
                }
            }
    }
}
