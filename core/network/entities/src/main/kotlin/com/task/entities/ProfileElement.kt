package com.task.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ProfileElement {
    val disabled: Boolean?
    val hide: Boolean?
    val id: String?
    val label: String?

    @Serializable
    data class Column(
        @SerialName("Disabled")
        override val disabled: Boolean?,
        @SerialName("Hide")
        override val hide: Boolean?,
        @SerialName("Id")
        override val id: String?,
        @SerialName("Label")
        override val label: String?,
        @SerialName("ProfileElements")
        val profileElements: List<ProfileElement>?
    ): ProfileElement

    @Serializable
    data class Birthday(
        @SerialName("Disabled")
        override val disabled: Boolean?,
        @SerialName("Hide")
        override val hide: Boolean?,
        @SerialName("Id")
        override val id: String?,
        @SerialName("Label")
        override val label: String?,
        @SerialName("IgnoreCustomerData")
        val ignoreCustomerData: Boolean?
    ): ProfileElement

    @Serializable
    data class Gender(
        @SerialName("Disabled")
        override val disabled: Boolean?,
        @SerialName("Hide")
        override val hide: Boolean?,
        @SerialName("Id")
        override val id: String?,
        @SerialName("Label")
        override val label: String?,
        @SerialName("GenderValueMaps")
        val genderValueMaps: List<GenderValueMap>?,
        @SerialName("IgnoreCustomerData")
        val ignoreCustomerData: Boolean?,
        @SerialName("SupportValues")
        val supportValues: List<String>?
    ): ProfileElement {
        @Serializable
        data class GenderValueMap(
            @SerialName("GenderType")
            val genderType: String?,
            @SerialName("Label")
            val label: String?
        )
    }

    @Serializable
    data class LastName(
        @SerialName("Disabled")
        override val disabled: Boolean?,
        @SerialName("Hide")
        override val hide: Boolean?,
        @SerialName("Id")
        override val id: String?,
        @SerialName("Label")
        override val label: String?,
        @SerialName("IgnoreCustomerData")
        val ignoreCustomerData: Boolean?
    ): ProfileElement

    @Serializable
    data class Row(
        @SerialName("Hide")
        override val hide: Boolean?,
        @SerialName("Id")
        override val id: String?,
        @SerialName("ProfileElements")
        val profileElements: List<ProfileElement>?,
        override val disabled: Boolean? = null,
        override val label: String? = null
    ): ProfileElement

    @Serializable
    data class Button(
        @SerialName("Action")
        val actions: List<ProfileAction>?,
        @SerialName("Disabled")
        override val disabled: Boolean?,
        @SerialName("Hide")
        override val hide: Boolean?,
        @SerialName("Id")
        override val id: String?,
        @SerialName("Label")
        override val label: String?
    ): ProfileElement

    data class Unknown(
        override val disabled: Boolean? = null,
        override val hide: Boolean? = null,
        override val id: String? = null,
        override val label: String? = null
    ) : ProfileElement
}
