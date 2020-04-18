package ee.aikada.psuinterface.helpers

import ee.aikada.psuinterface.models.enums.LimitType
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Format {
    private const val emptyValue = "-"
    private const val emptyTimeValue = "00:00:00"
    private val days = arrayOf(
        "Day", "Sun", "Mon", "Tue",
        "Wed", "Thur", "Fri", "Sat"
    )

    fun toCurrent(value: Any?): String {
        return if (value == null) emptyValue else "$value A"
    }

    fun toResistance(value: Any?): String {
        return if (value == null) emptyValue else "($value Ω)"
    }

    fun toVoltage(value: Any?): String {
        return if (value == null) emptyValue else "$value V"
    }

    fun toLimit(value: Any?, limitType: LimitType?): String {
        if (value == null && limitType == null) return emptyTimeValue
        val unit: String = limitType!!.unitName
        return "($value $unit)"
    }

    fun toTimeStart(startTimeMs: Long): String {
        return if (startTimeMs == 0L) emptyTimeValue else epochMillisToTime(
            startTimeMs
        )
    }

    fun epochMillisToDayAndTime(startTimeMs: Long?): String {
        if (startTimeMs == null) return ""
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

    fun millisToTime(millis: Long?): String {
        return if (millis != null) String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) -
                    TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millis)
                    ),
            TimeUnit.MILLISECONDS.toSeconds(millis) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millis)
                    )
        ) else emptyTimeValue

    }

    // https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
    fun dateStringToMillis(dateString: String?): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        val date: Date = sdf.parse(dateString)
        return date.time
    }
}