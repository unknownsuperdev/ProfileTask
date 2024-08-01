plugins {
    id("project.android.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.task.network.entities"
}

dependencies {
    // Serialization
    implementation(libs.kotlin.serialization)
}
