package com.task.profile

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.snapshots.SnapshotStateList

@Immutable
sealed interface ProfileElementUI {
    val disabled: Boolean
    val hide: Boolean
    val id: String
    val label: String
    val value: String

    @Immutable
    data class ColumnUI(
        override val disabled: Boolean,
        override val hide: Boolean,
        override val id: String,
        override val label: String,
        override val value: String = "",
        val profileElements: SnapshotStateList<ProfileElementUI>
    ): ProfileElementUI {
        override fun setValueWithCopy(value: String): ProfileElementUI = copy(value = value)
        override fun setHideWithCopy(hide: Boolean): ProfileElementUI = copy(hide = hide)
        override fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI = copy(disabled = disabled)
    }

    @Immutable
    data class BirthdayUI(
        override val disabled: Boolean,
        override val hide: Boolean,
        override val id: String,
        override val label: String,
        override val value: String = "",
        val ignoreCustomerData: Boolean
    ): ProfileElementUI {
        override fun setValueWithCopy(value: String): ProfileElementUI = copy(value = value)
        override fun setHideWithCopy(hide: Boolean): ProfileElementUI = copy(hide = hide)
        override fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI = copy(disabled = disabled)
    }

    @Immutable
    data class GenderUI(
        override val disabled: Boolean,
        override val hide: Boolean,
        override val id: String,
        override val label: String,
        override val value: String = "",
        val genderValueMaps: SnapshotStateList<GenderValueMap>,
        val ignoreCustomerData: Boolean,
        val supportValues: SnapshotStateList<String>
    ): ProfileElementUI {
        override fun setValueWithCopy(value: String): ProfileElementUI = copy(value = value)
        override fun setHideWithCopy(hide: Boolean): ProfileElementUI = copy(hide = hide)
        override fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI = copy(disabled = disabled)
        data class GenderValueMap(
            val genderType: String,
            val label: String
        )
    }

    @Immutable
    data class LastNameUI(
        override val disabled: Boolean,
        override val hide: Boolean,
        override val id: String,
        override val label: String,
        override val value: String = "",
        val ignoreCustomerData: Boolean
    ): ProfileElementUI {
        override fun setValueWithCopy(value: String): ProfileElementUI = copy(value = value)
        override fun setHideWithCopy(hide: Boolean): ProfileElementUI = copy(hide = hide)
        override fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI = copy(disabled = disabled)
    }

    @Immutable
    data class RowUI(
        override val hide: Boolean,
        override val id: String,
        override val value: String = "",
        val profileElements: SnapshotStateList<ProfileElementUI>,
        override val disabled: Boolean = false,
        override val label: String = ""
    ): ProfileElementUI {
        override fun setValueWithCopy(value: String): ProfileElementUI = copy(value = value)
        override fun setHideWithCopy(hide: Boolean): ProfileElementUI = copy(hide = hide)
        override fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI = copy(disabled = disabled)
    }

    @Immutable
    data class ButtonUI(
        val actions: List<ProfileActionUI>,
        override val disabled: Boolean,
        override val hide: Boolean,
        override val id: String,
        override val label: String,
        override val value: String = ""
    ): ProfileElementUI {
        override fun setValueWithCopy(value: String): ProfileElementUI = copy(value = value)
        override fun setHideWithCopy(hide: Boolean): ProfileElementUI = copy(hide = hide)
        override fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI = copy(disabled = disabled)
    }

    @Immutable
    data class UnknownUI(
        override val disabled: Boolean = false,
        override val hide: Boolean = false,
        override val id: String = "",
        override val value: String = "",
        override val label: String = ""
    ) : ProfileElementUI {
        override fun setValueWithCopy(value: String): ProfileElementUI = copy(value = value)
        override fun setHideWithCopy(hide: Boolean): ProfileElementUI = copy(hide = hide)
        override fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI = copy(disabled = disabled)
    }

    fun setValueWithCopy(value: String): ProfileElementUI
    fun setHideWithCopy(hide: Boolean): ProfileElementUI
    fun setDisabledWithCopy(disabled: Boolean): ProfileElementUI
}
