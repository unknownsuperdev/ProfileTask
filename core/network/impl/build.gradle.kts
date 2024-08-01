plugins {
    id("project.android.library")
}

android {
    namespace = "com.task.network.impl"
}

dependencies {

    implementation(project(":core:network:api"))
    implementation(project(":core:network:entities"))
    implementation(libs.bundles.ktor)

    // Chucker
    implementation(libs.chucker)

}
