plugins {
    alias(libs.plugins.shadowJar)
}

dependencies {
    compileOnly(libs.autoservice)
    annotationProcessor(libs.autoservice)

    compileOnly(libs.annotations)

    compileOnly(libs.gson)

    compileOnly(project(":core"))
}

tasks.shadowJar {
    archiveClassifier.set(null as String?)
}

tasks.build {
    dependsOn(tasks.shadowJar)
}