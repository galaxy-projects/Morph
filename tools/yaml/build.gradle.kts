dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.tools)
    compileOnly(libs.tools.dataformat.yaml)

    implementation(project(":tools"))
    compileOnly(project(":core"))
}