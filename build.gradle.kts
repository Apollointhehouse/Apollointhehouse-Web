plugins {
    kotlin("jvm") version "2.0.21"
}

group = "me.apollointhehouse"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.12.0")

    testImplementation(kotlin("test"))
}

tasks.jar {
    manifest.attributes["Main-Class"] = "me.apollointhehouse.MainKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}