package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileGroup(
    val name: String,
    val size: Int
)