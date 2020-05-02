package ee.aikada.psuinterface.controllers

import android.content.Context
import android.util.Log
import ee.aikada.psuinterface.R
import ee.aikada.psuinterface.mappers.ProfileGroupMapper
import ee.aikada.psuinterface.mappers.ProfileMapper
import ee.aikada.psuinterface.models.DTO.ProfileDTO
import ee.aikada.psuinterface.models.DTO.ProfileGroupDTO
import ee.aikada.psuinterface.models.ProfileGroups
import ee.aikada.psuinterface.models.Profiles
import ee.aikada.psuinterface.models.enums.ProfileType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.BufferedReader

class ProfileController(private var context: Context) {

    fun getProfileGroups(): List<ProfileGroupDTO> {
        val testData = context.resources.openRawResource(R.raw.test_data_profile_groups)
        val text = testData.bufferedReader().use(BufferedReader::readText)
        val jsonParser = Json(JsonConfiguration.Stable)
        val profileGroups = jsonParser.parse(ProfileGroups.serializer(), text)
        val profileGroupDTOs = mutableListOf<ProfileGroupDTO>()

        profileGroups.profileGroups?.forEach {
            profileGroupDTOs.add(ProfileGroupMapper.mapProfileGroup(it))
        }

        return profileGroupDTOs
    }

    private fun generateTestProfileGroups(): List<ProfileGroupDTO> {
        return listOf(
            ProfileGroupDTO("Test group 1"),
            ProfileGroupDTO("Test group 2")
        )
    }

    fun getProfilesForGroup(groupName: String): List<ProfileDTO> {
        val profilesItem = getProfiles()
        Log.d("getProfiles", profilesItem.toString())
        val profileDTOs = mutableListOf<ProfileDTO>()
        profilesItem.profiles.forEach {
            profileDTOs.add(ProfileMapper.mapProfile(it))
        }


        return profileDTOs
    }

    private fun generateTestProfiles(groupName: String): List<ProfileDTO> {
        val testData = listOf(
            ProfileDTO("Profile1", groupName, ProfileType.CC),
            ProfileDTO("Profile2", groupName, ProfileType.CV),
            ProfileDTO("Profile3", groupName, ProfileType.CV),
            ProfileDTO("Profile4", groupName, ProfileType.Graph),
            ProfileDTO("Profile5", groupName, ProfileType.CC),
            ProfileDTO("Profile6", groupName, ProfileType.CC),
            ProfileDTO("Profile7", groupName, ProfileType.CC)
        )

        testData[0].current = 10F
        testData[0].voltageLimit = 10F
        testData[0].duration = "10:09:09"

        testData[1].currentLimit = 10F
        testData[1].voltage = 10F
        testData[1].latchOff = true
        return testData
    }

    fun getProfiles(): Profiles {
        val testData = context.resources.openRawResource(R.raw.test_data_profiles)
        val text = testData.bufferedReader().use(BufferedReader::readText)

        val jsonParser = Json(JsonConfiguration.Stable.copy(ignoreUnknownKeys = true))
        return jsonParser.parse(Profiles.serializer(), text)
    }

    fun getTestProfilesForGroup(groupName: String): List<ProfileDTO> {
        val testData = listOf(
            ProfileDTO("Profile1", groupName, ProfileType.CC),
            ProfileDTO("Profile2", groupName, ProfileType.CV),
            ProfileDTO("Profile3", groupName, ProfileType.CV),
            ProfileDTO("Profile4", groupName, ProfileType.Graph),
            ProfileDTO("Profile5", groupName, ProfileType.CC),
            ProfileDTO("Profile6", groupName, ProfileType.CC),
            ProfileDTO("Profile7", groupName, ProfileType.CC)
        )

        testData[0].current = 10F
        testData[0].voltageLimit = 10F
        testData[0].duration = "10:09:09"

        testData[1].currentLimit = 10F
        testData[1].voltage = 10F
        testData[1].latchOff = true

        return testData

    }
}