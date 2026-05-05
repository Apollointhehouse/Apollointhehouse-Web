plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.serialization") version "2.3.21"
}

group = "me.apollointhehouse"
version = "1.0-SNAPSHOT"

val ktor_version: String by project
val log4jVersion = "2.25.4"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.12.0")
    implementation("org.jetbrains:markdown:0.7.3")

    implementation("io.ktor:ktor-client-logging:$ktor_version")
    runtimeOnly(platform("org.apache.logging.log4j:log4j-bom:$log4jVersion"))
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl")
    runtimeOnly("org.apache.logging.log4j:log4j-core")

    implementation("org.apache.xmlgraphics:batik-all:1.19")

    implementation(kotlin("reflect"))
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

    compilerOptions {
        freeCompilerArgs.add("-Xname-based-destructuring=complete")
    }
}
