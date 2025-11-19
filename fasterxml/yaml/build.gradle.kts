dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.fasterxml)
    compileOnly(libs.fasterxml.dataformat.yaml)

    implementation(project(":fasterxml"))
    compileOnly(project(":core"))
}