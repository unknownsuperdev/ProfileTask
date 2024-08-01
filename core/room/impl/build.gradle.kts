plugins {
    id("project.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.task.room.impl"
}

dependencies {
    implementation(project(":core:room:api"))
    implementation(project(":core:room:entities"))

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}
