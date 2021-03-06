package ee.aikada.psuinterface.ui.status

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ee.aikada.psuinterface.ProfileActivity
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.controllers.StatusController
import ee.aikada.psuinterface.models.DTO.StatusItemDTO

class StatusFragment : Fragment() {
    private val TAG = StatusFragment::class.java.simpleName
    private var recyclerAdapter: StatusRecyclerViewAdapter? = null

    var statusItems: List<StatusItemDTO> = listOf(
        StatusItemDTO("CH1"),
        StatusItemDTO("CH2")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view)
    }

    private fun setUpRecyclerView(view: View) {
        val statusController = StatusController(activity!!.applicationContext)
        statusItems = statusController.getStatusItemDTOs()
        recyclerAdapter =
            StatusRecyclerViewAdapter(statusItems) { channel -> openProfileActivity(channel) };
        view.findViewById<RecyclerView>(R.id.recycler_status_items).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }
    }

    companion object {
        fun newInstance(): StatusFragment = StatusFragment()
    }

    fun openProfileActivity(channel: Any) {
        Log.d(TAG, channel.toString())
        val intent = Intent(activity, ProfileActivity::class.java)
        intent.putExtra("channelName", channel.toString())
        activity?.startActivity(intent)
    }

}
