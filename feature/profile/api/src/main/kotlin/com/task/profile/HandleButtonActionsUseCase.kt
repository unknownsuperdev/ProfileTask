package com.task.profile

interface HandleButtonActionsUseCase {
    suspend operator fun invoke(actions: List<ProfileActionUI>)
}
