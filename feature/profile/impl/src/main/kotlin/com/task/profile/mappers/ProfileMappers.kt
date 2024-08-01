package com.task.profile.mappers

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import com.task.entities.ProfileAction
import com.task.entities.ProfileElement
import com.task.profile.ProfileActionUI
import com.task.profile.ProfileElementUI
import com.task.room.entities.ElementType
import com.task.room.entities.ProfileElementEntity
import com.task.utils.orDefault

fun ProfileElement.mapToUI(): ProfileElementUI = when (this) {
    is ProfileElement.Birthday -> ProfileElementUI.BirthdayUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id.orEmpty(),
        label = label.orEmpty(),
        ignoreCustomerData = ignoreCustomerData.orDefault()
    )
    is ProfileElement.Button -> ProfileElementUI.ButtonUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id.orEmpty(),
        label = label.orEmpty(),
        actions = actions?.map { action ->
            when (action) {
                is ProfileAction.DisableElementAction -> ProfileActionUI.DisableElementAction(elementId = action.elementId.orEmpty())
                is ProfileAction.EnableElementAction -> ProfileActionUI.EnableElementAction(elementId = action.elementId.orEmpty())
                is ProfileAction.HideElementAction -> ProfileActionUI.HideElementAction(elementId = action.elementId.orEmpty())
                is ProfileAction.ReloadDataAction -> ProfileActionUI.ReloadDataAction(emptyList())
                is ProfileAction.SaveCustomerAction -> ProfileActionUI.SaveCustomerAction(
                    birthdayId = action.birthdayId.orEmpty(),
                    birthdayValue = "",
                    genderId = action.genderId.orEmpty(),
                    genderValue = "",
                    lastNameId = action.lastNameId.orEmpty(),
                    lastNameValue = ""
                )
                is ProfileAction.ShowElementAction -> ProfileActionUI.ShowElementAction(elementId = action.elementId.orEmpty())
            }
        }.orEmpty()
    )
    is ProfileElement.Column -> ProfileElementUI.ColumnUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id.orEmpty(),
        label = label.orEmpty(),
        profileElements = profileElements?.map { it.mapToUI() }.orEmpty().toMutableStateList()
    )
    is ProfileElement.Gender -> ProfileElementUI.GenderUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id.orEmpty(),
        label = label.orEmpty(),
        genderValueMaps = genderValueMaps?.map {
            ProfileElementUI.GenderUI.GenderValueMap(
                genderType = it.genderType.orEmpty(),
                label = it.label.orEmpty()
            )
        }.orEmpty().toMutableStateList(),
        ignoreCustomerData = ignoreCustomerData.orDefault(),
        supportValues = supportValues.orEmpty().toMutableStateList()
    )
    is ProfileElement.LastName -> ProfileElementUI.LastNameUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id.orEmpty(),
        label = label.orEmpty(),
        ignoreCustomerData = ignoreCustomerData.orDefault()
    )
    is ProfileElement.Row -> ProfileElementUI.RowUI(
        hide = hide.orDefault(),
        id = id.orEmpty(),
        profileElements = profileElements?.map { it.mapToUI() }.orEmpty().toMutableStateList(),
        disabled = disabled.orDefault(),
        label = label.orEmpty()
    )
    is ProfileElement.Unknown -> ProfileElementUI.UnknownUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id.orEmpty(),
        label = label.orEmpty()
    )
}

fun ProfileElementUI.mapToEntity(): ProfileElementEntity = ProfileElementEntity(
    id = id,
    disabled = disabled,
    hide = hide,
    label = label,
    value = value,
    type = when (this) {
        is ProfileElementUI.BirthdayUI -> ElementType.BIRTHDAY
        is ProfileElementUI.ButtonUI -> ElementType.BUTTON
        is ProfileElementUI.ColumnUI -> ElementType.COLUMN
        is ProfileElementUI.GenderUI -> ElementType.GENDER
        is ProfileElementUI.LastNameUI -> ElementType.LAST_NAME
        is ProfileElementUI.RowUI -> ElementType.ROW
        is ProfileElementUI.UnknownUI -> ElementType.UNKNOWN
    }
)

fun ProfileElementEntity.mapToUI(): ProfileElementUI = when (type) {
    ElementType.COLUMN -> ProfileElementUI.ColumnUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id,
        label = label.orEmpty(),
        value = value.orEmpty(),
        profileElements = mutableStateListOf()
    )
    ElementType.LAST_NAME -> ProfileElementUI.LastNameUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id,
        label = label.orEmpty(),
        value = value.orEmpty(),
        ignoreCustomerData = false
    )
    ElementType.BUTTON -> ProfileElementUI.ButtonUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id,
        label = label.orEmpty(),
        value = value.orEmpty(),
        actions = emptyList()
    )
    ElementType.ROW -> ProfileElementUI.RowUI(
        hide = hide.orDefault(),
        id = id,
        profileElements = mutableStateListOf(),
        disabled = disabled.orDefault(),
        label = label.orEmpty(),
        value = value.orEmpty()
    )
    ElementType.BIRTHDAY -> ProfileElementUI.BirthdayUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id,
        label = label.orEmpty(),
        ignoreCustomerData = false,
        value = value.orEmpty()
    )
    ElementType.GENDER -> ProfileElementUI.GenderUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id,
        label = label.orEmpty(),
        genderValueMaps = mutableStateListOf(),
        ignoreCustomerData = false,
        supportValues = mutableStateListOf(),
        value = value.orEmpty()
    )
    ElementType.UNKNOWN -> ProfileElementUI.UnknownUI(
        disabled = disabled.orDefault(),
        hide = hide.orDefault(),
        id = id,
        label = label.orEmpty(),
        value = value.orEmpty()
    )
}
