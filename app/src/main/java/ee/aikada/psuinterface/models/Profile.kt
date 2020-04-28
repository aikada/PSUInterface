package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String,
    val group: String,
    val type: String,
    val duration_ms: Long?,
    val value: Float? = null,
    val limitValue: Float? = null,
    val resistance: Float? = null,
    val latchOff: Boolean? = false,
    val graph: Graph? = null
)