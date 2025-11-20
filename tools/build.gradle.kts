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

/**
 * dependencies {
 *     compileOnly(libs.autoservice)
 *     annotationProcessor(libs.autoservice)
 *
 *     compileOnly(libs.annotations)
 *
 *     compileOnly(libs.bundles.fasterxml)
 *     compileOnly(libs.fasterxml.dataformat.xml)
 *
 *     implementation(project(":fasterxml"))
 *     compileOnly(project(":core"))
 * }
 */