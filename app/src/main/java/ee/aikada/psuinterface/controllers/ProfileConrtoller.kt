package ee.aikada.psuinterface.controllers

import android.content.Context
import ee.aikada.psuinterface.DTO.ProfileDTO
import ee.aikada.psuinterface.DTO.ProfileGroupDTO

class ProfileController(private var context: Context) {

    fun getProfileGroups(): List<ProfileGroupDTO> {
        return listOf(
            ProfileGroupDTO("Test group 1"),
            ProfileGroupDTO("Test group 2")
        )
    }

    fun getProfilesForGroup(groupName: String): List<ProfileDTO> {
        return listOf(
            ProfileDTO("Profile1"),
            ProfileDTO("Profile2"),
            ProfileDTO("Profile3"),
            ProfileDTO("Profile4"),
            ProfileDTO("Profile5"),
            ProfileDTO("Profile6"),
            ProfileDTO("Profile7")
        )
    }

}
