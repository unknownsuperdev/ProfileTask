package com.task.profile.domain

import com.task.dispatchers.api.DispatchersProvider
import com.task.profile.HandleButtonActionsUseCase
import com.task.profile.ProfileActionUI
import com.task.profile.data.ProfileRepository
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class HandleButtonActionsUseCaseImpl(
    private val repository: ProfileRepository,
    private val dispatchers: DispatchersProvider
): HandleButtonActionsUseCase {
    override suspend fun invoke(actions: List<ProfileActionUI>) = withContext(dispatchers.io) {
        actions.forEach { action ->
            when (action) {
                is ProfileActionUI.DisableElementAction -> {
                    val element = repository.getElementById(action.elementId)
                    repository.updateElement(element.setDisabledWithCopy(true))
                }
                is ProfileActionUI.EnableElementAction -> {
                    val element = repository.getElementById(action.elementId)
                    repository.updateElement(element.setDisabledWithCopy(false))
                }
                is ProfileActionUI.HideElementAction -> {
                    val element = repository.getElementById(action.elementId)
                    repository.updateElement(element.setHideWithCopy(true))
                }
                is ProfileActionUI.ReloadDataAction -> {
                    repository.updateElements(elements = action.elements)
                }
                is ProfileActionUI.SaveCustomerAction -> {
                    val birthday = repository.getElementById(action.birthdayId)
                    repository.updateElement(birthday.setValueWithCopy(action.birthdayValue))
                    val gender = repository.getElementById(action.genderId)
                    repository.updateElement(gender.setValueWithCopy(action.genderValue))
                    val lastName = repository.getElementById(action.lastNameId)
                    repository.updateElement(lastName.setValueWithCopy(action.lastNameValue))
                }
                is ProfileActionUI.ShowElementAction -> {
                    val element = repository.getElementById(action.elementId)
                    repository.updateElement(element.setHideWithCopy(false))
                }
            }
        }
    }
}