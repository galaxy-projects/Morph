dependencies {
    getAll().forEach {
        implementation(it)
    }
}

tasks.jar {
    enabled = false
}

tasks.shadowJar {
    dependsOn(getAll().map { it.tasks.shadowJar })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    mergeServiceFiles()
    filesMatching("META-INF/services/**") {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = "org.galaxy.morph.providers.fasterxml"
            artifactId = "morph-provider-fasterxml-all"
            from(components["java"])
        }
    }
}

private fun getAll(): List<Project> {
    return parent?.subprojects?.filter { it != project } ?: listOf()
}