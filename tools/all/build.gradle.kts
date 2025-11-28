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
            groupId = "org.galaxy.morph.providers.tools"
            artifactId = "morph-provider-tools-all"
            from(components["java"])
        }
    }
}

private fun getAll(): List<Project> {
    return parent?.subprojects?.filter { it != project } ?: listOf()
}