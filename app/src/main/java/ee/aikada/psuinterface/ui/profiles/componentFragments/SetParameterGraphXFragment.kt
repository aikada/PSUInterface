package ee.aikada.psuinterface.ui.profiles.componentFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ee.aikada.psuinterface.DTO.GraphXDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.ui.profiles.ProfileAddEditViewModel


private const val ARG_TITLE = "param1"
private const val ARG_VALUE = "param2"
private const val ARG_UNIT = "param3"
private const val ARG_POINTS = "param4"


class SetParameterGraphXFragment : Fragment() {
    val TAG = SetParameterGraphFragment::class.java.simpleName

    private var title: String? = null
    private var value: Float? = null
    private var unit: String? = null
    private var graphXDTO: GraphXDTO? = null
    var mEditTextValue: EditText? = null
    private lateinit var viewModel: ProfileAddEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            value = it.getFloat(ARG_VALUE)
            unit = it.getString(ARG_UNIT)
            graphXDTO = it.getParcelable(ARG_POINTS)
        }
        viewModel = ViewModelProvider(requireActivity()).get(ProfileAddEditViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_set_parameter_graph_x, container, false)
        mEditTextValue = v.findViewById(R.id.fragment_set_parameter_graph_x_value)

        mEditTextValue!!.setText(value!!.toString())
        v.findViewById<TextView>(R.id.fragment_set_parameter_graph_x_name).text = title
        v.findViewById<TextView>(R.id.fragment_set_parameter_graph_x_unit).text = unit

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mEditTextValue!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                viewModel.setGraphX(charSequence.toString())
                Log.d(TAG, charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    companion object {
        fun newInstance(
            title: String,
            value: Float,
            unit: String,
            graphXDTO: GraphXDTO
        ) =
            SetParameterGraphXFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putFloat(ARG_VALUE, value)
                    putString(ARG_UNIT, unit)
                    putParcelable(ARG_POINTS, graphXDTO)
                }
            }
    }
}
