package com.task.network.api

import com.task.entities.ProfileElement
import kotlinx.coroutines.flow.Flow

interface ProfileApi {
    fun getProfileContent(): Flow<List<ProfileElement>>
}
