dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.bundles.fasterxml)

    implementation(project(":fasterxml"))
    compileOnly(project(":core"))
}


publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = "org.galaxy.morph.providers.fasterxml"
            artifactId = "morph-provider-fasterxml-json"
            from(components["java"])
        }
    }
}