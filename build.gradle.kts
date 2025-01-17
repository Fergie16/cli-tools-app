plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "1.9.10" // Serialization plugin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.fusesource.jansi:jansi:2.4.1") //for colour
    implementation("io.ktor:ktor-client-core:2.3.2")
    implementation("io.ktor:ktor-client-cio:2.3.2") // HTTP engine
    implementation("io.ktor:ktor-client-content-negotiation:2.3.2") // JSON support
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2") // JSON serialization
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(15)
}