plugins {
    id("com.google.cloud.tools.jib") version "2.3.0"
    kotlin("jvm") version "1.7.10"
    application
}

val junitVersion= "5.8.2"
val http4kVersion= "4.29.0.0"
val kotlinVersion= "1.7.0"


jib {
    container.mainClass = "com.example.calendarbackendhttp4kKt"
    to.image = "docker_repo_user/docker_image_user"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.http4k:http4k-client-okhttp:${http4kVersion}")
    implementation("org.http4k:http4k-contract:${http4kVersion}")
    implementation("org.http4k:http4k-core:${http4kVersion}")
    implementation("org.http4k:http4k-format-argo:${http4kVersion}")
    implementation("org.http4k:http4k-format-jackson:${http4kVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    testImplementation("org.http4k:http4k-testing-hamkrest:${http4kVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    implementation("dev.forkhandles:result4k:1.8.2.0")


}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
