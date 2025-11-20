dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.tools)

    implementation(project(":tools"))
    compileOnly(project(":core"))
}