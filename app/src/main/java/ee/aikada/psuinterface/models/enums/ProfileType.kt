package ee.aikada.psuinterface.models.enums

import ee.aikada.psuinterface.DTO.StatusFieldsDTO
import ee.aikada.psuinterface.helpers.Format

enum class ProfileType(className: String) {
    CC("CC"),
    CV("CV"),
    Graph("Graph") {
        override fun formatField3(value: StatusFieldsDTO) =
            "Graph"
    };

    open fun formatField1(value: StatusFieldsDTO): String = Format.toVoltage(value.voltage)
    open fun formatField2(value: StatusFieldsDTO): String = Format.toCurrent(value.current)
    open fun formatField3(value: StatusFieldsDTO): String =
        Format.toLimit(value.limit, value.limitType)
    open fun formatField4(value: StatusFieldsDTO): String = Format.toResistance(value.resistance)
    open fun formatTimeField1(value: StatusFieldsDTO): String = getTimeProgressFormat(value)
    open fun formatTimeField2(value: StatusFieldsDTO): String = getTimeLeftFormat(value)
    open fun formatTimeField3(value: StatusFieldsDTO): String = getEstimatedTimePeriodFormat(value)
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
