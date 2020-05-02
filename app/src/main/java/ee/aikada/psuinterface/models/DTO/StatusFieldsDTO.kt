package ee.aikada.psuinterface.models.DTO

import ee.aikada.psuinterface.models.enums.LimitType

data class StatusFieldsDTO(
    var voltage: Number = 0,
    var current: Number = 0
    ) {
        var limit: Number? = null
        var limitType: LimitType? = null
        var resistance: Number? = null
        var startTime: Long? = null
        var duration_ms: Long? = null
        var runtime_ms: Long? = null
}