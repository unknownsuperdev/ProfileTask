package com.task.network.impl.utils

import com.task.entities.ProfileAction
import com.task.entities.ProfileElement
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.core.annotation.Single

@Single
class ProfileElementListParser(private val json: Json) {

    fun parse(jsonString: String): List<ProfileElement> {
        val profileElements = mutableListOf<ProfileElement>()
        val rootObject = json.parseToJsonElement(jsonString).jsonObject
        val jsonElements = rootObject["ProfileElements"]?.jsonArray.orEmpty()
        jsonElements.forEach { jsonObject ->
            profileElements.addAll(parseElements(jsonObject))
        }
        return profileElements
    }

    private fun parseElements(jsonObject: JsonElement): List<ProfileElement> {
        return jsonObject.jsonObject.keys.map { key ->
            when (key) {
                "Column" -> jsonObject.jsonObject[key]?.let { parseColumnElement(it) }
                "Birthday" -> jsonObject.jsonObject[key]?.let { parseBirthdayElement(it) }
                "Gender" -> jsonObject.jsonObject[key]?.let { parseGenderElement(it) }
                "LastName" -> jsonObject.jsonObject[key]?.let { parseLastNameElement(it) }
                "Row" -> jsonObject.jsonObject[key]?.let { parseRowElement(it) }
                "Button" -> jsonObject.jsonObject[key]?.let { parseButtonElement(it) }
                else -> ProfileElement.Unknown()
            } ?: ProfileElement.Unknown()
        }
    }

    private fun parseColumnElement(jsonObject: JsonElement): ProfileElement.Column {
        val column = jsonObject.jsonObject
        val elements = mutableListOf<ProfileElement>()
        column["ProfileElements"]?.jsonArray?.forEach { elementObject ->
            elements.addAll(parseElements(elementObject))
        }
        return ProfileElement.Column(
            disabled = column["Disabled"]?.jsonPrimitive?.boolean,
            hide = column["Hide"]?.jsonPrimitive?.boolean,
            id = column["Id"]?.jsonPrimitive?.contentOrNull,
            label = column["Label"]?.jsonPrimitive?.contentOrNull,
            profileElements = elements
        )
    }

    private fun parseBirthdayElement(jsonObject: JsonElement): ProfileElement.Birthday {
        val birthday = jsonObject.jsonObject
        return ProfileElement.Birthday(
            disabled = birthday["Disabled"]?.jsonPrimitive?.boolean,
            hide = birthday["Hide"]?.jsonPrimitive?.boolean,
            id = birthday["Id"]?.jsonPrimitive?.contentOrNull,
            label = birthday["Label"]?.jsonPrimitive?.contentOrNull,
            ignoreCustomerData = birthday["IgnoreCustomerData"]?.jsonPrimitive?.boolean
        )
    }

    private fun parseGenderElement(jsonObject: JsonElement): ProfileElement.Gender {
        val gender = jsonObject.jsonObject
        return ProfileElement.Gender(
            disabled = gender["Disabled"]?.jsonPrimitive?.boolean,
            hide = gender["Hide"]?.jsonPrimitive?.boolean,
            id = gender["Id"]?.jsonPrimitive?.contentOrNull,
            label = gender["Label"]?.jsonPrimitive?.contentOrNull,
            ignoreCustomerData = gender["IgnoreCustomerData"]?.jsonPrimitive?.boolean,
            genderValueMaps = gender["GenderValueMaps"]?.let { json.decodeFromJsonElement(it) },
            supportValues = gender["SupportValues"]?.let { json.decodeFromJsonElement(it) },
        )
    }

    private fun parseLastNameElement(jsonObject: JsonElement): ProfileElement.LastName {
        val lastName = jsonObject.jsonObject
        return ProfileElement.LastName(
            disabled = lastName["Disabled"]?.jsonPrimitive?.boolean,
            hide = lastName["Hide"]?.jsonPrimitive?.boolean,
            id = lastName["Id"]?.jsonPrimitive?.contentOrNull,
            label = lastName["Label"]?.jsonPrimitive?.contentOrNull,
            ignoreCustomerData = lastName["IgnoreCustomerData"]?.jsonPrimitive?.boolean
        )
    }

    private fun parseRowElement(jsonObject: JsonElement): ProfileElement.Row {
        val row = jsonObject.jsonObject
        val elements = mutableListOf<ProfileElement>()
        row["ProfileElements"]?.jsonArray?.forEach { elementObject ->
            elements.addAll(parseElements(elementObject))
        }
        return ProfileElement.Row(
            disabled = row["Disabled"]?.jsonPrimitive?.boolean,
            hide = row["Hide"]?.jsonPrimitive?.boolean,
            id = row["Id"]?.jsonPrimitive?.contentOrNull,
            label = row["Label"]?.jsonPrimitive?.contentOrNull,
            profileElements = elements
        )
    }

    private fun parseButtonElement(jsonObject: JsonElement): ProfileElement.Button {
        val button = jsonObject.jsonObject
        return ProfileElement.Button(
            disabled = button["Disabled"]?.jsonPrimitive?.boolean,
            hide = button["Hide"]?.jsonPrimitive?.boolean,
            id = button["Id"]?.jsonPrimitive?.contentOrNull,
            label = button["Label"]?.jsonPrimitive?.contentOrNull,
            actions = button["Action"]?.let { parseAction(it) }
        )
    }

    private fun parseAction(jsonObject: JsonElement): List<ProfileAction> {
        val actionList = mutableListOf<ProfileAction>()
        jsonObject.jsonObject.keys.forEach { key ->
            when (key) {
                "ActionGroup" -> jsonObject.jsonObject[key]?.let { parseAction(it) }
                "Actions" -> jsonObject.jsonObject[key]?.jsonArray?.map { parseAction(it) }?.flatten()
                "ShowElementAction" -> jsonObject.jsonObject[key]?.let {
                    listOf(json.decodeFromJsonElement<ProfileAction.ShowElementAction>(it))
                }
                "HideElementAction" -> jsonObject.jsonObject[key]?.let {
                    listOf(json.decodeFromJsonElement<ProfileAction.HideElementAction>(it))
                }
                "EnableElementAction" -> jsonObject.jsonObject[key]?.let {
                    listOf(json.decodeFromJsonElement<ProfileAction.EnableElementAction>(it))
                }
                "DisableElementAction" -> jsonObject.jsonObject[key]?.let {
                    listOf(json.decodeFromJsonElement<ProfileAction.DisableElementAction>(it))
                }
                "SaveCustomerAction" -> jsonObject.jsonObject[key]?.let {
                    listOf(json.decodeFromJsonElement<ProfileAction.SaveCustomerAction>(it))
                }
                "ReloadDataAction" -> listOf(ProfileAction.ReloadDataAction)
                else -> null
            }?.let {
                actionList.addAll(it)
            }
        }
        return actionList
    }
}