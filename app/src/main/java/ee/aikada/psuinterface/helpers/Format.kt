package ee.aikada.psuinterface.helpers

import ee.aikada.psuinterface.DTO.GraphYDTO
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.models.enums.GraphType
import ee.aikada.psuinterface.models.enums.LimitType
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Format {
    private const val emptyValue = ""
    private const val emptyTimeValue = "00:00:00"
    private val days = arrayOf(
        "Day", "Sun", "Mon", "Tue",
        "Wed", "Thu", "Fri", "Sat"
    )

    fun toCurrent(value: Any?): String {
        return if (value == null) emptyValue else "$value A"
    }

    fun toResistance(value: Any?): String {
        return if (value == null || value == 0F) emptyValue else "($value Ω)"
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

    fun epochMillisToDayAndTime(timeMs: Long?): String {
        if (timeMs == null) return ""
        val cal = GregorianCalendar()
        cal.timeInMillis = timeMs

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

    fun toGraphVoltage(value: ProfileDTO): String {
        var returnStr: String = ""
        val yList = value.graph!!.y
        val voltage: GraphYDTO? = findGraphTypeInYList(yList, GraphType.Voltage)
        val voltageLimit: GraphYDTO? = findGraphTypeInYList(yList, GraphType.VoltageLimit)

        if (voltage != null) {
            returnStr = if (voltage.points.size == 1) {
                toVoltage(voltage.points[0][1])
            } else {
                toVoltage(toMinMax(voltage.points))
            }
        } else if (voltageLimit != null) {
            returnStr = if (voltageLimit.points.size == 1) {
                toLimit(voltageLimit.points[0][1], LimitType.V)
            } else {
                toLimit(toMinMax(voltageLimit.points), LimitType.V)
            }
        }

        return returnStr
    }

    fun toGraphCurrent(value: ProfileDTO): String {
        var returnStr: String = ""
        val yList = value.graph!!.y
        val current: GraphYDTO? = findGraphTypeInYList(yList, GraphType.Current)
        val currentLimit: GraphYDTO? = findGraphTypeInYList(yList, GraphType.CurrentLimit)

        if (current != null) {
            returnStr = if (current.points.size == 1) {
                toCurrent(current.points[0][1])
            } else {
                toCurrent(toMinMax(current.points))
            }
        } else if (currentLimit != null) {
            returnStr = if (currentLimit.points.size == 1) {
                toLimit(currentLimit.points[0][1], LimitType.C)
            } else {
                toLimit(toMinMax(currentLimit.points), LimitType.C)
            }
        }

        return returnStr
    }

    private fun toMinMax(points: List<Array<Float>>): String {
        val min = findMin(points)[1]
        val max = findMax(points)[1]
        return "($min – $max)"
    }

    private fun findMin(points: List<Array<Float>>): Array<Float> {
        var min = points[0]
        for (point in points) {
            if (point[1] < min[1]) {
                min = point
            }
        }
        return min
    }

    private fun findMax(points: List<Array<Float>>): Array<Float> {
        var max = points[0]
        for (point in points) {
            if (point[1] > max[1]) {
                max = point
            }
        }
        return max
    }

    private fun findGraphTypeInYList(
        yList: List<GraphYDTO>,
        graphType: GraphType
    ): GraphYDTO? {
        for (y in yList) {
            if (y.value == graphType) {
                return y
            }
        }
        return null
    }
}