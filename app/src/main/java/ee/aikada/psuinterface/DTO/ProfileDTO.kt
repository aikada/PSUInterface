package ee.aikada.psuinterface.DTO

import ee.aikada.psuinterface.models.enums.ProfileType

data class ProfileDTO(
    var profileName: String = "<template>",
    var profileGroupName: String = "<template>",
    var profileType: ProfileType = ProfileType.CV
) {
    var voltage: Float? = null
    var current: Float? = null
    var voltageLimit: Float? = null
    var currentLimit: Float? = null

    var resistance: Float? = null
    var duration: String? = null
    var latchOff: Boolean? = null

    var graph: GraphDTO? = null
}
