package ee.aikada.psuinterface.DTO

import java.sql.Timestamp

data class StatusFieldsDTO(
    var field1: String = "1",
    var field2: String = "2",
    var field3: String = "3",
    var field4: String = "4",
    var timeStart: Long? = System.currentTimeMillis(), //start time
    var timeRuntime: Long? = 0,
    var timeDuration: Long? = 0
)