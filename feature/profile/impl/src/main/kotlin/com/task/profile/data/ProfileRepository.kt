package com.task.profile.data

import com.task.profile.ProfileElementUI
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfileContent(): Flow<List<ProfileElementUI>>

    fun getProfileConfig(): Flow<List<ProfileElementUI>>

    suspend fun updateElement(element: ProfileElementUI)

    suspend fun updateElements(elements: List<ProfileElementUI>)

    suspend fun insertElements(elements: List<ProfileElementUI>)

    suspend fun deleteElements(elements: List<ProfileElementUI>)

    suspend fun deleteAllElements(elements: List<ProfileElementUI>)

    suspend fun getElementById(elementId: String): ProfileElementUI
}
