package ee.aikada.psuinterface.ui.profiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ee.aikada.psuinterface.models.DTO.ProfileDTO


class ProfileAddEditViewModel : ViewModel() {
    var channelName: String? = null
    var groupName: String? = null
    var profile: ProfileDTO? = null
    private val mGraphX = MutableLiveData<String>()

    fun setGraphX(name: String) {
        mGraphX.value = name
    }

    fun getGraphX(): LiveData<String>? {
        return mGraphX
    }

}
