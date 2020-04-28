package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class StatusItem(
    val channels: Array<Int>,
    val profile: String?,
    val profileType: String?,
    val paused: Boolean? = null,
    val status: StatusFields? = null
)