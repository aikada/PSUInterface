package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class StatusFields(
    var voltage: Float = 0F,
    var current: Float = 0F,
    var limit: Float?,
    var limitType: String?,
    var resistance: Float?,
    var startTime: String?,
    var duration_ms: Long?,
    var runtime_ms: Long?
)