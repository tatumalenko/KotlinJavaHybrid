plugins {
    java
    kotlin("jvm") version "1.7.22"
    application
}

group = "io.tatumalenko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test")) // The Kotlin test library
}

application {
    mainClass.set("io.tatumalenko.KotlinJavaHybridKt") // The main class of the application
}
