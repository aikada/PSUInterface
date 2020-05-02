package ee.aikada.psuinterface.mappers

import ee.aikada.psuinterface.models.DTO.ProfileGroupDTO
import ee.aikada.psuinterface.models.ProfileGroup

object ProfileGroupMapper {
    fun mapProfileGroup(profile: ProfileGroup): ProfileGroupDTO {
        return ProfileGroupDTO(profile.name, profile.size)
    }
}