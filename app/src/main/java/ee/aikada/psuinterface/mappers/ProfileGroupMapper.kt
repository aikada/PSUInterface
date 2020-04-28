package ee.aikada.psuinterface.mappers

import ee.aikada.psuinterface.DTO.*
import ee.aikada.psuinterface.models.*

object ProfileGroupMapper {
    fun mapProfileGroup(profile: ProfileGroup): ProfileGroupDTO {
        return ProfileGroupDTO(profile.name, profile.size)
    }
}