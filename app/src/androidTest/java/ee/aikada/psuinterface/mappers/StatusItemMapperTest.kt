package ee.aikada.psuinterface.mappers

import androidx.test.platform.app.InstrumentationRegistry
import ee.aikada.psuinterface.models.StatusItem
import org.junit.Test

import org.junit.Assert.*

class StatusItemMapperTest {

    @Test
    fun map() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

//        val mapStatusItem = StatusItemMapper.mapStatusItem(StatusItem(Array(1, [1] ), "", "", null))
        assertEquals("ee.aikada.psuinterface", appContext.packageName)
    }
}
