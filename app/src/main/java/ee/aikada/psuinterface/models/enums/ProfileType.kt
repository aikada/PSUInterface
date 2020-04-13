package ee.aikada.psuinterface.models.enums

import ee.aikada.psuinterface.models.StatusFields
import ee.aikada.psuinterface.helpers.Format

enum class ProfileType(className: String) {
    CC("CC") {
        override fun formatField1(value: StatusFields) = Format.toVoltage(value.field1)
        override fun formatField2(value: StatusFields) = Format.toCurrent(value.field2)
        override fun formatField3(value: StatusFields) =
            Format.toVoltageWithBraces(value.field3)

        override fun formatField4(value: StatusFields) = Format.toResistance(value.field4)
        override fun formatTimeField1(value: StatusFields) =
            "${Format.millisToTime(value.timeDurationRunning)} / ${Format.millisToTime(value.timeDurationTotal)}"

        override fun formatTimeField2(value: StatusFields) =
            "Time left:${Format.millisToTime(value.timeDurationTotal - value.timeDurationRunning)}"

        override fun formatTimeField3(value: StatusFields) =
            "${Format.epochMillisToDayAndTime(value.timeStart)} – ${Format.epochMillisToDayAndTime(
                value.timeStart + value.timeDurationTotal
            )}"
    },
    CV("CV") {
        override fun formatField1(value: StatusFields) = Format.toVoltage(value.field1)
        override fun formatField2(value: StatusFields) = Format.toCurrent(value.field2)
        override fun formatField3(value: StatusFields) =
            Format.toVoltageWithBraces(value.field3)

        override fun formatField4(value: StatusFields) = Format.toResistance(value.field4)
        override fun formatTimeField1(value: StatusFields) =
            "${Format.millisToTime(value.timeDurationRunning)} / ${Format.millisToTime(value.timeDurationTotal)}"

        override fun formatTimeField2(value: StatusFields) =
            "Time left:${Format.millisToTime(value.timeDurationTotal - value.timeDurationRunning)}"

        override fun formatTimeField3(value: StatusFields) =
            "${Format.epochMillisToDayAndTime(value.timeStart)} – ${Format.epochMillisToDayAndTime(
                value.timeStart + value.timeDurationTotal
            )}"
    },
    Graph("Graph") {
        override fun formatField1(value: StatusFields) = Format.toVoltage(value.field1)
        override fun formatField2(value: StatusFields) = Format.toCurrent(value.field2)
        override fun formatField3(value: StatusFields) =
            Format.toVoltageWithBraces(value.field3)

        override fun formatField4(value: StatusFields) = Format.toResistance(value.field4)
        override fun formatTimeField1(value: StatusFields) =
            "${Format.millisToTime(value.timeDurationRunning)} / ${Format.millisToTime(value.timeDurationTotal)}"

        override fun formatTimeField2(value: StatusFields) =
            "Time left:${Format.millisToTime(value.timeDurationTotal - value.timeDurationRunning)}"

        override fun formatTimeField3(value: StatusFields) =
            "${Format.epochMillisToDayAndTime(value.timeStart)} – ${Format.epochMillisToDayAndTime(
                value.timeStart + value.timeDurationTotal
            )}"
    };

    abstract fun formatField1(value: StatusFields): String
    abstract fun formatField2(value: StatusFields): String
    abstract fun formatField3(value: StatusFields): String
    abstract fun formatField4(value: StatusFields): String
    abstract fun formatTimeField1(value: StatusFields): String
    abstract fun formatTimeField2(value: StatusFields): String
    abstract fun formatTimeField3(value: StatusFields): String
}
