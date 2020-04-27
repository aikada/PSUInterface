package ee.aikada.psuinterface.DTO

import ee.aikada.psuinterface.models.enums.LimitType

data class ProfileGroupDTO(
    var profileGroupName: String = "test",
    var profileCount: Int = 0
)