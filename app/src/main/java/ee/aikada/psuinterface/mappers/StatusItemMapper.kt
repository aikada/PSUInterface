package ee.aikada.psuinterface.mappers

import ee.aikada.psuinterface.DTO.StatusFieldsDTO
import ee.aikada.psuinterface.DTO.StatusItemDTO
import ee.aikada.psuinterface.helpers.Format
import ee.aikada.psuinterface.models.StatusFields
import ee.aikada.psuinterface.models.StatusItem
import ee.aikada.psuinterface.models.enums.LimitType
import ee.aikada.psuinterface.models.enums.ProfileType
import java.text.SimpleDateFormat
import java.util.*

object StatusItemMapper {
    fun mapStatusItem(statusItem: StatusItem): StatusItemDTO {
        val channelName = statusItem.channels.joinToString("+", "CH")
        val profileNameAndGroup = statusItem.profile?.split("/")?.toTypedArray()
        val currentProfile =
            if (statusItem.profileType == null) null else ProfileType.valueOf(statusItem.profileType.toString())

        val statusItemDTO = StatusItemDTO(channelName)
        statusItemDTO.profileGroup = profileNameAndGroup?.get(0) ?: ""
        statusItemDTO.profileName = profileNameAndGroup?.get(1) ?: ""
        statusItemDTO.profileType = currentProfile
        statusItemDTO.statusFields = statusItem.status?.let { mapStatusFields(it) }

        return statusItemDTO
    }

    fun mapStatusFields(statusFields: StatusFields): StatusFieldsDTO {
        val statusFieldsDTO = StatusFieldsDTO()

        statusFieldsDTO.voltage = statusFields.voltage
        statusFieldsDTO.current = statusFields.current

        statusFieldsDTO.limit = statusFields.limit
        statusFieldsDTO.limitType = LimitType.valueOf(statusFields.limitType.toString())
        statusFieldsDTO.resistance = statusFields.resistance

        statusFieldsDTO.startTime = Format.dateStringToMillis(statusFields.startTime)
        statusFieldsDTO.runtime_ms = statusFields.runtime_ms
        statusFieldsDTO.duration_ms = statusFields.duration_ms

        return statusFieldsDTO
    }
}