package ee.aikada.psuinterface.DTO

import ee.aikada.psuinterface.models.enums.LimitType
import ee.aikada.psuinterface.models.enums.ProfileType

data class ProfileDTO(
    var profileName: String = "test",
    var profileGroupName: String = "test",
    var profileType: ProfileType = ProfileType.CC
)