package com.task.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PROFILE_ELEMENTS)
data class ProfileElementEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "disabled")
    val disabled: Boolean?,
    @ColumnInfo(name = "hide")
    val hide: Boolean?,
    @ColumnInfo(name = "label")
    val label: String?,
    @ColumnInfo(name = "value")
    val value: String? = null,
    @ColumnInfo(name = "type")
    val type: ElementType
)
