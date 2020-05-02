package ee.aikada.psuinterface.models.DTO

import android.os.Parcelable
import ee.aikada.psuinterface.models.enums.GraphType
import kotlinx.android.parcel.Parcelize

data class GraphDTO (var x: GraphXDTO, var y: List<GraphYDTO>)

@Parcelize
data class GraphXDTO(var value: GraphType?, var offSet: Float, var scale: Float) : Parcelable

@Parcelize
data class GraphYDTO(var value: GraphType?, var offSet: Float, var scale: Float, var points: MutableList<Array<Float>>) :
    Parcelable