package ee.aikada.psuinterface.models.enums

import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.DTO.StatusFieldsDTO
import ee.aikada.psuinterface.helpers.Format

enum class ProfileType(val profileTypeName: String) {
    CV("CV"){
        override fun formatField2(value: ProfileDTO) =
            Format.toLimit(value.currentLimit, LimitType.C)
    },
    CC("CC") {
        override fun formatField1(value: ProfileDTO) =
            Format.toLimit(value.voltageLimit, LimitType.V)
    },

    Graph("Graph") {
        override fun formatField1(value: ProfileDTO): String = Format.toGraphVoltage(value)
        override fun formatField2(value: ProfileDTO): String = Format.toGraphCurrent(value)
    };

    // status view formatting
    open fun formatField1(value: StatusFieldsDTO): String = Format.toVoltage(value.voltage)
    open fun formatField2(value: StatusFieldsDTO): String = Format.toCurrent(value.current)
    open fun formatField3(value: StatusFieldsDTO): String =
        Format.toLimit(value.limit, value.limitType)

    open fun formatField4(value: StatusFieldsDTO): String = Format.toResistance(value.resistance)
    open fun formatTimeField1(value: StatusFieldsDTO): String = getTimeProgressFormat(value)
    open fun formatTimeField2(value: StatusFieldsDTO): String = getTimeLeftFormat(value)
    open fun formatTimeField3(value: StatusFieldsDTO): String = getEstimatedTimePeriodFormat(value)

    // profile view formatting
    open fun formatField1(value: ProfileDTO): String = Format.toVoltage(value.voltage)
    open fun formatField2(value: ProfileDTO): String = Format.toCurrent(value.current)
    open fun formatField3(value: ProfileDTO): String = " "
    open fun formatField4(value: ProfileDTO): String = Format.toResistance(value.resistance)
    open fun formatTimeField1(value: ProfileDTO): String = value.duration.toString()
    open fun formatTimeField2(value: ProfileDTO): String = "Latch off: " + value.latchOff

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
