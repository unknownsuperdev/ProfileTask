package com.task.profile

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.task.mvi.MviState

@Immutable
data class ProfileState(
    override val error: String? = null,
    val elements: SnapshotStateList<ProfileElementUI> = mutableStateListOf(),
    val oldElements: SnapshotStateList<ProfileElementUI> = mutableStateListOf()
): MviState {
    fun findElementById(id: String) = elements.findElementById(id)

    private fun SnapshotStateList<ProfileElementUI>.findElementById(
        elementId: String
    ): ProfileElementUI? {
        var element: ProfileElementUI? = null
        for (el in this) {
            if (el.id == elementId) {
                element = el
                break
            }
        }
        return if (element != null) element else {
            var nestedElement: ProfileElementUI? = null
            for (el in this) {
                when (el) {
                    is ProfileElementUI.ColumnUI -> {
                        nestedElement = el.profileElements.findElementById(elementId)
                        if (nestedElement != null) break
                    }
                    is ProfileElementUI.RowUI -> {
                        nestedElement = el.profileElements.findElementById(elementId)
                        if (nestedElement != null) break
                    }
                    else -> Unit
                }
            }
            nestedElement
        }
    }
}
