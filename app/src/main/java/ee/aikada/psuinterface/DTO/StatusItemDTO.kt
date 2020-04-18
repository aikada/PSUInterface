package ee.aikada.psuinterface.DTO

import ee.aikada.psuinterface.models.enums.ProfileType

data class StatusItemDTO(val channelName: String) {
    var profileGroup: String = "none"
    var profileName: String = "none"
    var profileType: ProfileType = ProfileType.CC
    var statusFields: StatusFieldsDTO =
        StatusFieldsDTO(
            "F1",
            "F2",
            "F3",
            "F4"
        )
}