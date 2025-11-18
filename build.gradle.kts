plugins {
    `java-library`
}

group = "org.galaxy.morph"
version = "0.0.1"

allprojects {
    apply(plugin = "java-library")

    group = rootProject.group
    version = rootProject.version

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    repositories {
        mavenCentral()
    }
}