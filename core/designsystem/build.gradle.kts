plugins {
    id("project.android.library")
    id("project.android.library.compose")
}
android {
    namespace = "com.task.designsystem"
}
dependencies {

    implementation(libs.android.core.ktx)
    implementation(libs.appcompat)


}
