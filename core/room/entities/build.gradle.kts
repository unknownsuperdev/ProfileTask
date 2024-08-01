plugins {
    id("project.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.task.room.entities"
}

dependencies {
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
