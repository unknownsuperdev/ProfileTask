package com.project.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

enum class FlavorDimension {
    contentType
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
enum class Flavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val appName: String,
    val appIcon: String,
    val appIconRound: String,
    val url: String,
    val protocol: String,
    val photoUrl: String,
    val apiKey: String
) {
    dev(
        dimension = FlavorDimension.contentType,
        applicationIdSuffix = ".dev",
        appName = "@string/app_name",
        appIcon = "@mipmap/ic_launcher",
        appIconRound = "@mipmap/ic_launcher_round",
        url = "api.themoviedb.org/3",
        protocol = "HTTPS",
        photoUrl = "https://image.tmdb.org/t/p/",
        apiKey = "67f98f4f2dbe19a881b6d2f4166362cc"
    ),
    stage(
        dimension = FlavorDimension.contentType,
        applicationIdSuffix = ".stage",
        appName = "@string/app_name",
        appIcon = "@mipmap/ic_launcher",
        appIconRound = "@mipmap/ic_launcher_round",
        url = "api.themoviedb.org/3",
        protocol = "HTTPS",
        photoUrl = "https://image.tmdb.org/t/p/",
        apiKey = "67f98f4f2dbe19a881b6d2f4166362cc"
    ),
    prod(
        dimension = FlavorDimension.contentType,
        appName = "@string/app_name",
        appIcon = "@mipmap/ic_launcher",
        appIconRound = "@mipmap/ic_launcher_round",
        url = "api.themoviedb.org/3",
        protocol = "HTTPS",
        photoUrl = "https://image.tmdb.org/t/p/",
        apiKey = "67f98f4f2dbe19a881b6d2f4166362cc"
    )
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            Flavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            this.applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                    buildConfigField("String", "BASE_URL", "\"${it.url}\"")
                    buildConfigField("String", "PROTOCOL", "\"${it.protocol}\"")
                    buildConfigField("String", "PHOTO_URL", "\"${it.photoUrl}\"")
                    buildConfigField("String", "API_KEY", "\"${it.apiKey}\"")
                    addManifestPlaceholders(
                        mapOf(
                            "appIcon" to it.appIcon,
                            "appIconRound" to it.appIconRound,
                            "appName" to it.appName
                        )
                    )
                }
            }
        }
    }
}
