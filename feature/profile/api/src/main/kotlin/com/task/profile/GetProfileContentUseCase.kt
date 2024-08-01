package com.task.profile

import kotlinx.coroutines.flow.Flow

interface GetProfileContentUseCase {
    operator fun invoke(): Flow<List<ProfileElementUI>>
}
