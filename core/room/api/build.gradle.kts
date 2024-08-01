plugins {
    id("project.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.task.room.api"
}

dependencies {
    implementation(project(":core:room:entities"))

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}
