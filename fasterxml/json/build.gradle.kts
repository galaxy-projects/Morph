dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.fasterxml)

    implementation(project(":fasterxml"))
    compileOnly(project(":core"))
}