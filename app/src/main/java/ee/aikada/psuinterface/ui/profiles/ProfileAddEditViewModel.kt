package ee.aikada.psuinterface.ui.profiles

import androidx.lifecycle.ViewModel
import ee.aikada.psuinterface.DTO.ProfileDTO

class ProfileAddEditViewModel : ViewModel() {
    var channelName: String? = null
    var groupName: String? = null
    var profile: ProfileDTO? = null

}
