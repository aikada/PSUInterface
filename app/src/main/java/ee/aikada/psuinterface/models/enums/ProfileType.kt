package ee.aikada.psuinterface.models.enums

import ee.aikada.psuinterface.DTO.StatusFieldsDTO
import ee.aikada.psuinterface.helpers.Format

enum class ProfileType(className: String) {
    CC("CC") {
        override fun formatField1(value: StatusFieldsDTO) = Format.toVoltage(value.field1)
        override fun formatField2(value: StatusFieldsDTO) = Format.toCurrent(value.field2)
        override fun formatField3(value: StatusFieldsDTO) = Format.toVoltageWithBraces(value.field3)
        override fun formatField4(value: StatusFieldsDTO) = Format.toResistance(value.field4)
        override fun formatTimeField1(value: StatusFieldsDTO) = getTimeProgressFormat(value)
        override fun formatTimeField2(value: StatusFieldsDTO) = getTimeLeftFormat(value)
        override fun formatTimeField3(value: StatusFieldsDTO) = getEstimatedTimePeriodFormat(value)
    },
    CV("CV") {
        override fun formatField1(value: StatusFieldsDTO) = Format.toVoltage(value.field1)
        override fun formatField2(value: StatusFieldsDTO) = Format.toCurrent(value.field2)
        override fun formatField3(value: StatusFieldsDTO) = Format.toVoltageWithBraces(value.field3)
        override fun formatField4(value: StatusFieldsDTO) = Format.toResistance(value.field4)
        override fun formatTimeField1(value: StatusFieldsDTO) = getTimeProgressFormat(value)
        override fun formatTimeField2(value: StatusFieldsDTO) = getTimeLeftFormat(value)
        override fun formatTimeField3(value: StatusFieldsDTO) = getEstimatedTimePeriodFormat(value)
    },
    Graph("Graph") {
        override fun formatField1(value: StatusFieldsDTO) = Format.toVoltage(value.field1)
        override fun formatField2(value: StatusFieldsDTO) = Format.toCurrent(value.field2)
        override fun formatField3(value: StatusFieldsDTO) = Format.toVoltageWithBraces(value.field3)
        override fun formatField4(value: StatusFieldsDTO) = Format.toResistance(value.field4)
        override fun formatTimeField1(value: StatusFieldsDTO) = getTimeProgressFormat(value)
        override fun formatTimeField2(value: StatusFieldsDTO) = getTimeLeftFormat(value)
        override fun formatTimeField3(value: StatusFieldsDTO) = getEstimatedTimePeriodFormat(value)
    };

    abstract fun formatField1(value: StatusFieldsDTO): String
    abstract fun formatField2(value: StatusFieldsDTO): String
    abstract fun formatField3(value: StatusFieldsDTO): String
    abstract fun formatField4(value: StatusFieldsDTO): String
    abstract fun formatTimeField1(value: StatusFieldsDTO): String
    abstract fun formatTimeField2(value: StatusFieldsDTO): String
    abstract fun formatTimeField3(value: StatusFieldsDTO): String
}

private fun getTimeProgressFormat(value: StatusFieldsDTO) =
    "${Format.millisToTime(value.timeRuntime)} / ${Format.millisToTime(value.timeDuration)}"

private fun getTimeLeftFormat(value: StatusFieldsDTO) =
    if (value.timeDuration != null && value.timeRuntime != null)
        "Time left: ${Format.millisToTime(value.timeDuration!! - value.timeRuntime!!)}" else ""

private fun getEstimatedTimePeriodFormat(value: StatusFieldsDTO): String {
    if (value.timeDuration != null && value.timeStart != null)
        return "${Format.epochMillisToDayAndTime(value.timeStart)} – ${Format.epochMillisToDayAndTime(
            value.timeStart!! + value.timeDuration!!
        )}"
    return ""
}
