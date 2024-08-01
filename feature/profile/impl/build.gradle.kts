plugins {
    id("project.android.library")
    id("project.android.library.compose")
    id("project.android.feature")
}
android {
    namespace = "com.task.feature.profile"
}
dependencies {

    implementation(project(":core:room:api"))
    implementation(project(":core:room:entities"))
    implementation(project(":feature:profile:api"))
    implementation(libs.material3.android)
}