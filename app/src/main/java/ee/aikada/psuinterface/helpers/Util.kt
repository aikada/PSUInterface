package ee.aikada.psuinterface.helpers

import ee.aikada.psuinterface.models.enums.GraphType

object Util {
    fun graphTypeFor(value: String) =
        GraphType.values().firstOrNull { it.jsonName == value }
}