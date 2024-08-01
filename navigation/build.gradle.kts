plugins {
    id("project.android.library")
    id("project.android.library.compose")
}

android {
    namespace = "com.task.navigation"
}

dependencies {
    implementation(project(":feature:profile:impl"))
}
