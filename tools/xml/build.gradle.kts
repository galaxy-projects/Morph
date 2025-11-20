dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.tools)
    compileOnly(libs.tools.dataformat.xml)

    implementation(project(":tools"))
    compileOnly(project(":core"))
}