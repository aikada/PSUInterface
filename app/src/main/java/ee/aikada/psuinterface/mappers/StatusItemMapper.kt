package ee.aikada.psuinterface.mappers

import ee.aikada.psuinterface.DTO.StatusFieldsDTO
import ee.aikada.psuinterface.DTO.StatusItemDTO
import ee.aikada.psuinterface.helpers.Format
import ee.aikada.psuinterface.models.StatusFields
import ee.aikada.psuinterface.models.StatusItem
import ee.aikada.psuinterface.models.enums.ProfileType
import java.text.SimpleDateFormat
import java.util.*

object StatusItemMapper {
    fun mapStatusItem(statusItem: StatusItem): StatusItemDTO {
        val channelName = statusItem.channels.joinToString("+", "CH")
        val profileNameAndGroup = statusItem.profile?.split("/")?.toTypedArray()
        val currentProfile = ProfileType.valueOf(statusItem.profileType.toString())

        val statusItemDTO = StatusItemDTO(channelName)
        statusItemDTO.profileGroup = profileNameAndGroup?.get(0) ?: ""
        statusItemDTO.profileName = profileNameAndGroup?.get(1) ?: ""

        statusItemDTO.profileType = currentProfile
        if (statusItem.status != null) {
            statusItemDTO.statusFields = mapStatusFields(statusItem.status!!, currentProfile)
        }

        return statusItemDTO
    }

    fun mapStatusFields(statusFields: StatusFields, profileType: ProfileType): StatusFieldsDTO {
        val statusFieldsDTO = StatusFieldsDTO()

        statusFieldsDTO.field1 = statusFields.voltage.toString()
        statusFieldsDTO.field2 = statusFields.current.toString()
        when (profileType) {
            ProfileType.CC -> {
                statusFieldsDTO.field3 = statusFields.resistance.toString()
                statusFieldsDTO.field4 = statusFields.limit.toString()
            }
            ProfileType.CV -> {
                statusFieldsDTO.field3 = statusFields.resistance.toString()
                statusFieldsDTO.field4 = statusFields.limit.toString()
            }

            else -> {
                statusFieldsDTO.field3 = statusFields.resistance.toString()
                statusFieldsDTO.field4 = ""
            }
        }

        statusFieldsDTO.timeStart = Format.dateStringToMillis(statusFields.startTime)
        statusFieldsDTO.timeRuntime = statusFields.runtime_ms
        statusFieldsDTO.timeDuration = statusFields.duration_ms

        return statusFieldsDTO
    }
}