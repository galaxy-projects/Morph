dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.fasterxml)
    compileOnly(libs.fasterxml.dataformat.toml)

    implementation(project(":fasterxml"))
    compileOnly(project(":core"))
}