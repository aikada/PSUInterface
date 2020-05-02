package ee.aikada.psuinterface.models.enums

import ee.aikada.psuinterface.helpers.Format
import ee.aikada.psuinterface.models.DTO.ProfileDTO
import ee.aikada.psuinterface.models.DTO.StatusFieldsDTO

enum class ProfileType(val profileTypeName: String) {
    CV("CV") {
        override fun formatField2(profile: ProfileDTO) =
            Format.toLimit(profile.currentLimit, LimitType.C)
    },
    CC("CC") {
        override fun formatField1(profile: ProfileDTO) =
            Format.toLimit(profile.voltageLimit, LimitType.V)
    },
    Graph("Graph") {
        override fun formatField1(profile: ProfileDTO): String = Format.toGraphVoltage(profile)
        override fun formatField2(profile: ProfileDTO): String = Format.toGraphCurrent(profile)
    };

    open fun formatField1(profile: ProfileDTO): String = Format.toVoltage(profile.voltage)
    open fun formatField2(profile: ProfileDTO): String = Format.toCurrent(profile.current)
    open fun formatField3(profile: ProfileDTO): String = ""
    open fun formatField4(profile: ProfileDTO): String = Format.toResistance(profile.resistance)
    open fun formatTimeField1(profile: ProfileDTO): String = profile.duration.toString()
    open fun formatTimeField2(profile: ProfileDTO): String = "Latch off: " + profile.latchOff

    open fun formatField1(status: StatusFieldsDTO): String = Format.toVoltage(status.voltage)
    open fun formatField2(status: StatusFieldsDTO): String = Format.toCurrent(status.current)
    open fun formatField3(status: StatusFieldsDTO): String =
        Format.toLimit(status.limit, status.limitType)
    open fun formatField4(status: StatusFieldsDTO): String = Format.toResistance(status.resistance)
    open fun formatTimeField1(status: StatusFieldsDTO): String = getTimeProgressFormat(status)
    open fun formatTimeField2(status: StatusFieldsDTO): String = getTimeLeftFormat(status)
    open fun formatTimeField3(status: StatusFieldsDTO): String = getEstimatedTimePeriodFormat(status)
}

private fun getTimeProgressFormat(value: StatusFieldsDTO) =
    "${Format.millisToTime(value.runtime_ms)} / ${Format.millisToTime(value.duration_ms)}"

private fun getTimeLeftFormat(value: StatusFieldsDTO) =
    if (value.duration_ms != null && value.runtime_ms != null)
        "Time left: ${Format.millisToTime(value.duration_ms!! - value.runtime_ms!!)}" else ""

private fun getEstimatedTimePeriodFormat(value: StatusFieldsDTO): String {
    if (value.duration_ms != null && value.startTime != null)
        return "${Format.epochMillisToDayAndTime(value.startTime)} – ${Format.epochMillisToDayAndTime(
            value.startTime!! + value.duration_ms!!
        )}"
    return ""
}
