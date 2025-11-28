dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.tools)
    compileOnly(libs.tools.dataformat.xml)

    implementation(project(":tools"))
    compileOnly(project(":core"))
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = "org.galaxy.morph.providers.tools"
            artifactId = "morph-provider-tools-xml"
            from(components["java"])
        }
    }
}