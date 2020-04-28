package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class Graph(
    val X: GraphX,
    val Y: Array<GraphY>
)


@Serializable
class GraphX (
    val value: String = "time",
    val offset: Float = 0F,
    val scale: Float = 1F
)

@Serializable
class GraphY(
    val value: String,
    val offset: Float = 0F,
    val scale: Float = 1F,
    val points: Array<Array<Float>>
)
