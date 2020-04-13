package ee.aikada.psuinterface.helpers

import java.util.*
import java.util.concurrent.TimeUnit

object Format {
    private const val emptyValue = "-"
    private const val emptyTimeValue = "00:00:00"
    private val days = arrayOf(
        "Day", "Sun", "Mon", "Tue",
        "Wed", "Thur", "Fri", "Sat"
    )

    fun toCurrent(value: String): String {
        return if (value == "") emptyValue else "$value As"
    }

    fun toResistance(value: String): String {
        return if (value == "") emptyValue else "($value Ω)"
    }

    fun toVoltage(value: String): String {
        return if (value == "") emptyValue else "$value V"
    }

    fun toVoltageWithBraces(value: String): String {
        return if (value == "") emptyValue else "($value V)"
    }

    fun toTimeStart(startTimeMs: Long): String {
        return if (startTimeMs == 0L) emptyTimeValue else epochMillisToTime(
            startTimeMs
        )
    }

    fun epochMillisToDayAndTime(startTimeMs: Long): String {
        val cal = GregorianCalendar()
        cal.timeInMillis = startTimeMs

        val dayOfWeek = days[cal.get(GregorianCalendar.DAY_OF_WEEK)]
        val hh = cal.get(GregorianCalendar.HOUR_OF_DAY).toString().padStart(2, '0')
        val mm = cal.get(GregorianCalendar.MINUTE).toString().padStart(2, '0')
        val ss = cal.get(GregorianCalendar.SECOND).toString().padStart(2, '0')

        return "$dayOfWeek $hh:$mm:$ss"
    }

    fun epochMillisToTime(startTimeMs: Long): String {
        val cal = GregorianCalendar()
        cal.timeInMillis = startTimeMs

        val hh = cal.get(GregorianCalendar.HOUR_OF_DAY).toString().padStart(2, '0')
        val mm = cal.get(GregorianCalendar.MINUTE).toString().padStart(2, '0')
        val ss = cal.get(GregorianCalendar.SECOND).toString().padStart(2, '0')

        return "$hh:$mm:$ss"
    }

    fun millisToTime(millis: Long): String {
        return String.format("%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) -
                    TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millis)),
            TimeUnit.MILLISECONDS.toSeconds(millis) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millis)))

    }
}