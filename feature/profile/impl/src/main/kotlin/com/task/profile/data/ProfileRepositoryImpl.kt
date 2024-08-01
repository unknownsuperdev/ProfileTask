package com.task.profile.data

import com.task.network.api.ProfileApi
import com.task.profile.ProfileElementUI
import com.task.profile.mappers.mapToEntity
import com.task.profile.mappers.mapToUI
import com.task.room.api.ElementDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
internal class ProfileRepositoryImpl(
    private val profileApi: ProfileApi,
    private val elementDao: ElementDao
) : ProfileRepository {

    override fun getProfileContent(): Flow<List<ProfileElementUI>> =
        profileApi.getProfileContent().map { list -> list.map { it.mapToUI() } }

    override fun getProfileConfig(): Flow<List<ProfileElementUI>> =
        elementDao.selectAllFlow().map { list -> list.map { it.mapToUI() } }

    override suspend fun updateElement(element: ProfileElementUI) =
        elementDao.update(element.mapToEntity())

    override suspend fun updateElements(elements: List<ProfileElementUI>) =
        elementDao.update(elements.map { it.mapToEntity() })

    override suspend fun insertElements(elements: List<ProfileElementUI>) =
        elementDao.insertAll(elements.map { it.mapToEntity() })

    override suspend fun deleteElements(elements: List<ProfileElementUI>) =
        elementDao.delete(elements.map { it.mapToEntity() })

    override suspend fun deleteAllElements(elements: List<ProfileElementUI>) =
        elementDao.deleteAll()

    override suspend fun getElementById(elementId: String): ProfileElementUI =
        elementDao.selectById(elementId).mapToUI()
}
