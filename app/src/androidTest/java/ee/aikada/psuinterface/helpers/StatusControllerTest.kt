package ee.aikada.psuinterface.helpers

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

class StatusControllerTest {

    @Test
    fun getData() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val statusController: StatusController = StatusController(appContext)
        val data = statusController.getStatusItemDTOs()
        println("E");

        print(data)
        System.err.println("E")
    }
}