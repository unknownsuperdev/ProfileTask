package com.task.network.impl.services

import android.content.Context
import com.task.entities.ProfileElement
import com.task.network.api.ProfileApi
import com.task.network.impl.extention.emitFlow
import com.task.network.impl.utils.ProfileElementListParser
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class ProfileApiImpl(
    private val context: Context,
    private val elementsParser: ProfileElementListParser
): ProfileApi {

    override fun getProfileContent(): Flow<List<ProfileElement>> = emitFlow {
        val jsonString = context.assets.open(PROFILE_FILE_ASSET)
            .bufferedReader()
            .readLines()
            .joinToString(separator = "\n")
        elementsParser.parse(jsonString)
    }

    companion object {
        private const val PROFILE_FILE_ASSET = "MobileEditProfile.json"
    }
}
