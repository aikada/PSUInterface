package ee.aikada.psuinterface.DTO

import ee.aikada.psuinterface.models.enums.GraphType

data class GraphDTO (var x: GraphXDTO, var y: List<GraphYDTO>) {

}

data class GraphXDTO(var value: GraphType?, var offSet: Float, var scale: Float) {

}

data class GraphYDTO(var value: GraphType?, var offSet: Float, var scale: Float, var points: List<Array<Float>>) {

}