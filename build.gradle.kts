plugins {
    java
    application
    kotlin("jvm") version "2.1.10"
    id("com.palantir.git-version") version "3.1.0"
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "xmc"
version = "1.0-SNAPSHOT"

val javafxVersion = "17.0.11"

application {
    mainClass = "XMCBootstrapKt"
    applicationDefaultJvmArgs = listOf("-Xmx512m")
}

javafx {
    version = javafxVersion
    modules = listOf("javafx.controls")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.12.1")
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("org.openjfx:javafx-base:$javafxVersion")
    implementation("org.openjfx:javafx-controls:$javafxVersion")
    implementation("org.openjfx:javafx-graphics:$javafxVersion")
    implementation("net.java.dev.jna:jna:5.13.0")
    implementation("net.java.dev.jna:jna-platform:5.13.0")
}

tasks {
    test {
        useJUnitPlatform()
    }

    val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
    val details = versionDetails()

    processResources {
        filteringCharset = Charsets.UTF_8.name()
        val properties = inputs.properties.map {
            it.key to it.value
        }.toMap(hashMapOf()).apply {
            this["version"] = version
            this["commitHash"] = details.gitHash
        }
        filesMatching("version_info.json") { expand(properties) }
    }
}

kotlin {
    jvmToolchain(21)
}