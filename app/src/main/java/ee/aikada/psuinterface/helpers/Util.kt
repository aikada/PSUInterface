package ee.aikada.psuinterface.helpers

import ee.aikada.psuinterface.models.DTO.GraphDTO
import ee.aikada.psuinterface.models.DTO.GraphXDTO
import ee.aikada.psuinterface.models.DTO.GraphYDTO
import ee.aikada.psuinterface.models.DTO.ProfileDTO
import ee.aikada.psuinterface.models.enums.GraphType

object Util {
    fun graphTypeFor(value: String) =
        GraphType.values().firstOrNull { it.jsonName == value }

    fun initGraphPropertyForProfile(profile: ProfileDTO): ProfileDTO {
        if (profile.graph == null) {
            val graphXDTO = GraphXDTO(GraphType.Time, 0F, 1F)

            val graphYDTOs = listOf(
                GraphYDTO(GraphType.Voltage, 0F, 0F, mutableListOf()),
                GraphYDTO(GraphType.CurrentLimit, 0F, 0F, mutableListOf()),
                GraphYDTO(GraphType.Resistance, 0F, 0F, mutableListOf())
            )


            profile.graph = GraphDTO(graphXDTO, graphYDTOs)
        }
        return profile
    }
}

