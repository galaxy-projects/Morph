dependencies {
    compileOnly(libs.annotations)
    compileOnly(libs.slf4j)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.launcher)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            artifactId = "morph-core"
            from(components["java"])
        }
    }
}