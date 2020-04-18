package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class StatusPackage (val ch: Array<StatusItem>) {
}