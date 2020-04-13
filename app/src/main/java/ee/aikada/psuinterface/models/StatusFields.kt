package ee.aikada.psuinterface.models

import java.sql.Timestamp

data class StatusFields(
    var field1: String = "1",
    var field2: String = "2",
    var field3: String = "3",
    var field4: String = "4",
    var timeStart: Long = System.currentTimeMillis(), //start time
    var timeDurationRunning: Long = 80000,
    var timeDurationTotal: Long = 800000
) {
}