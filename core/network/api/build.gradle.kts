plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.task.network.api"
}

dependencies {
    implementation(project(":core:network:entities"))
    // Serialization
    implementation(libs.kotlin.serialization)
}
