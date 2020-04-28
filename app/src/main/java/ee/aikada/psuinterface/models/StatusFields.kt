package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class StatusFields(
    val voltage: Float = 0F,
    val current: Float = 0F,
    val limit: Float?,
    val limitType: String?,
    val resistance: Float?,
    val startTime: String?,
    val duration_ms: Long?,
    val runtime_ms: Long?
)