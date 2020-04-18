package ee.aikada.psuinterface.helpers

import android.content.Context
import ee.aikada.psuinterface.DTO.StatusItemDTO
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.mappers.StatusItemMapper
import ee.aikada.psuinterface.models.StatusItem
import ee.aikada.psuinterface.models.StatusPackage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.BufferedReader

class StatusController(private var context: Context) {

    fun getStatusItemDTOs(): List<StatusItemDTO> {
        val statusPackage = getStatusPackage()
        val statusItems: MutableList<StatusItemDTO> = mutableListOf<StatusItemDTO>()

        statusPackage.ch.forEach {
            statusItems.add(StatusItemMapper.mapStatusItem(it))
        }

        return ArrayList(statusItems)
    }

    fun getStatusPackage(): StatusPackage {
        val testData = context.resources.openRawResource(R.raw.test_data);
        val text = testData.bufferedReader().use(BufferedReader::readText)
        val jsonParser = Json(JsonConfiguration.Stable)

        return jsonParser.parse(StatusPackage.serializer(), text);
    }


    fun channelDataToStatusPackage() {
        context.resources.openRawResource(R.raw.test_data);

//        applicationContext
//        val json = {}
//        val file_name = "qjsonfile.json"
//        val json_string = applicationContext.assets.open(file_name).bufferedReader().use{
//            it.readText()
//        }
    }

}
