package com.task.profile.domain

import com.task.dispatchers.api.DispatchersProvider
import com.task.profile.GetProfileContentUseCase
import com.task.profile.ProfileElementUI
import com.task.profile.data.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class GetProfileContentUseCaseImpl(
    private val repository: ProfileRepository,
    private val dispatcher: DispatchersProvider
): GetProfileContentUseCase {
    override fun invoke(): Flow<List<ProfileElementUI>> {
        val profileContent = repository.getProfileContent().flowOn(dispatcher.io)
        val profileConfig = repository.getProfileConfig().flowOn(dispatcher.io)
        return profileContent.combine(profileConfig) { content, config ->
            val configAll = config.getAllElements()
            val contentAll = content.getAllElements()
            val configIds = configAll.map { it.id }
            val notExistingElements = contentAll.filter { it.id !in configIds }
            repository.insertElements(notExistingElements)
            val contentIds = contentAll.map { it.id }
            val removedElements = configAll.filter { it.id !in contentIds }
            repository.deleteElements(removedElements)
            return@combine content.mergeConfig(configAll)
        }.flowOn(dispatcher.io)
    }
}
