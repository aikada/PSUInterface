package ee.aikada.psuinterface.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileGroups(val profileGroups: Array<ProfileGroup>? = null)