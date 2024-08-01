package com.task.profile

sealed interface ProfileActionUI {
    class HideElementAction(
        val elementId: String
    ) : ProfileActionUI

    class ShowElementAction(
        val elementId: String
    ) : ProfileActionUI

    class EnableElementAction(
        val elementId: String
    ) : ProfileActionUI

    class DisableElementAction(
        val elementId: String
    ) : ProfileActionUI

    data class SaveCustomerAction(
        val birthdayId: String,
        val birthdayValue: String,
        val genderId: String,
        val genderValue: String,
        val lastNameId: String,
        val lastNameValue: String
    ) : ProfileActionUI

    data class ReloadDataAction(val elements: List<ProfileElementUI>) : ProfileActionUI
}
