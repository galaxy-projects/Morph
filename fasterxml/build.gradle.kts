plugins {
    alias(libs.plugins.shadowJar)
}

subprojects {
    apply(plugin = "com.gradleup.shadow")

    tasks.shadowJar {
        archiveClassifier.set("")
    }

    tasks.build {
        dependsOn(tasks.shadowJar)
    }
}

dependencies {
    compileOnly(libs.annotations)

    compileOnly(libs.bundles.fasterxml)

    compileOnly(project(":core"))
}