package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class StatusItem(
    val channels: Array<Int>,
    var profile: String?,
    var profileType: String?,
    var status: StatusFields?
)