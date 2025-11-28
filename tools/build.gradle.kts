plugins {
    alias(libs.plugins.shadowJar)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

subprojects {
    apply(plugin = "com.gradleup.shadow")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    tasks.shadowJar {
        archiveClassifier.set("")
    }

    tasks.build {
        dependsOn(tasks.shadowJar)
    }
}

dependencies {
    compileOnly(libs.annotations)

    compileOnly(libs.bundles.tools)

    compileOnly(project(":core"))
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = "org.galaxy.morph.providers.tools"
            artifactId = "morph-provider-tools-base"
            from(components["java"])
        }
    }
}