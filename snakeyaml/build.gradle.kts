dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.snakeyaml)

    compileOnly(project(":core"))
}


publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = "org.galaxy.morph.providers"
            artifactId = "morph-provider-snakeyaml"
            from(components["java"])
        }
    }
}