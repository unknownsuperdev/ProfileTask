package com.task.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ProfileAction {
    @Serializable
    class HideElementAction(
        @SerialName("ProfileElementId")
        val elementId: String?
    ) : ProfileAction

    @Serializable
    class ShowElementAction(
        @SerialName("ProfileElementId")
        val elementId: String?
    ) : ProfileAction

    @Serializable
    class EnableElementAction(
        @SerialName("ProfileElementId")
        val elementId: String?
    ) : ProfileAction

    @Serializable
    class DisableElementAction(
        @SerialName("ProfileElementId")
        val elementId: String?
    ) : ProfileAction

    @Serializable
    data class SaveCustomerAction(
        @SerialName("BirthdayId")
        val birthdayId: String?,
        @SerialName("GenderId")
        val genderId: String?,
        @SerialName("LastNameId")
        val lastNameId: String?
    ) : ProfileAction

    @Serializable
    data object ReloadDataAction : ProfileAction
}
