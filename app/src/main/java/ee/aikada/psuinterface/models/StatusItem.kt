package ee.aikada.psuinterface.models

import ee.aikada.psuinterface.models.enums.ProfileType

data class StatusItem(val channelName: String) {
    var profileGroup: String = "none"
    var profileName: String = "none"
    var profileType: ProfileType = ProfileType.CC
    var statusFields: Any = StatusFields(
        "F1",
        "F2",
        "F3",
        "F4"
    )
}