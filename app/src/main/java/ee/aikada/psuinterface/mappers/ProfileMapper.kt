package ee.aikada.psuinterface.mappers

import ee.aikada.psuinterface.helpers.Format
import ee.aikada.psuinterface.helpers.Util
import ee.aikada.psuinterface.models.DTO.GraphDTO
import ee.aikada.psuinterface.models.DTO.GraphXDTO
import ee.aikada.psuinterface.models.DTO.GraphYDTO
import ee.aikada.psuinterface.models.DTO.ProfileDTO
import ee.aikada.psuinterface.models.Graph
import ee.aikada.psuinterface.models.GraphX
import ee.aikada.psuinterface.models.GraphY
import ee.aikada.psuinterface.models.Profile
import ee.aikada.psuinterface.models.enums.ProfileType

object ProfileMapper {
    fun mapProfile(profile: Profile): ProfileDTO {
        val profileDTO = ProfileDTO(profile.name, profile.group, ProfileType.valueOf(profile.type))
        profileDTO.resistance = profile.resistance
        profileDTO.latchOff = profile.latchOff
        profileDTO.duration = Format.millisToTime(profile.duration_ms)

        when (profileDTO.profileType) {
            ProfileType.CV -> {
                profileDTO.voltage = profile.value
                profileDTO.currentLimit = profile.limitValue
            }
            ProfileType.CC -> {
                profileDTO.current = profile.value
                profileDTO.voltageLimit = profile.limitValue
            }
            ProfileType.Graph -> {
                profileDTO.graph = mapGraph(profile.graph!!)
            }
        }
        return profileDTO
    }

    fun mapGraph(graph: Graph): GraphDTO? {
        val graphXDTO = mapGraphX(graph.X)
        val graphYDTOList = mapGraphY(graph.Y)

        return GraphDTO(graphXDTO, graphYDTOList)
    }

    fun mapGraphX(graphX: GraphX): GraphXDTO {
        return GraphXDTO(Util.graphTypeFor(graphX.value), graphX.offset, graphX.scale)
    }

    fun mapGraphY(graphY: Array<GraphY>): MutableList<GraphYDTO> {
        val graphYDTOList = mutableListOf<GraphYDTO>()
        graphY.forEach {
            val graphYDTO = GraphYDTO(
                Util.graphTypeFor(it.value), it.offset, it.scale,
                it.points.toMutableList()
            )
            graphYDTOList.add(graphYDTO)
        }
        return graphYDTOList
    }

}