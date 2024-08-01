package com.task.profile.domain

import androidx.compose.runtime.toMutableStateList
import com.task.profile.ProfileElementUI

fun ProfileElementUI.mergeConfig(config: ProfileElementUI, fullConfig: List<ProfileElementUI>) = when (this) {
    is ProfileElementUI.BirthdayUI -> copy(
        disabled = config.disabled,
        hide = config.hide,
        label = config.label,
        value = config.value
    )
    is ProfileElementUI.ButtonUI -> copy(
        disabled = config.disabled,
        hide = config.hide,
        label = config.label,
        value = config.value
    )
    is ProfileElementUI.ColumnUI -> copy(
        disabled = config.disabled,
        hide = config.hide,
        label = config.label,
        value = config.value,
        profileElements = profileElements.toList().mergeConfig(fullConfig).toMutableStateList()
    )
    is ProfileElementUI.GenderUI -> copy(
        disabled = config.disabled,
        hide = config.hide,
        label = config.label,
        value = config.value
    )
    is ProfileElementUI.LastNameUI -> copy(
        disabled = config.disabled,
        hide = config.hide,
        label = config.label,
        value = config.value
    )
    is ProfileElementUI.RowUI -> copy(
        disabled = config.disabled,
        hide = config.hide,
        label = config.label,
        value = config.value,
        profileElements = profileElements.toList().mergeConfig(fullConfig).toMutableStateList()
    )
    is ProfileElementUI.UnknownUI -> copy(
        disabled = config.disabled,
        hide = config.hide,
        label = config.label,
        value = config.value
    )
}

fun List<ProfileElementUI>.getAllElements(): List<ProfileElementUI> {
    val elements = mutableListOf<ProfileElementUI>()
    forEach { element ->
        when (element) {
            is ProfileElementUI.ColumnUI -> {
                elements.add(element)
                elements.addAll(element.profileElements.getAllElements())
            }
            is ProfileElementUI.RowUI -> {
                elements.add(element)
                elements.addAll(element.profileElements.getAllElements())
            }
            else -> elements.add(element)
        }
    }
    return elements
}

fun List<ProfileElementUI>.mergeConfig(config: List<ProfileElementUI>): List<ProfileElementUI> =
    map { element ->
        config.find { it.id == element.id }?.let {
            element.mergeConfig(config = it, fullConfig = config)
        } ?: element
    }
