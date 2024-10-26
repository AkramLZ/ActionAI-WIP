import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.3"
}

group = "com.ultimismc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.checkerframework:checker-qual:3.48.1")
    implementation("org.checkerframework:checker-qual:3.48.1")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.14")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.moandjiezana.toml:toml4j:0.7.2")

    implementation("net.dv8tion:JDA:5.1.2") {
        exclude(module = "opus-java")
    }

    for (file in File("libs").listFiles()!!) {
        implementation(files(file))
    }

}

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "com.akraml.actionai.Main"
}

tasks.withType<ShadowJar> {
    archiveFileName.set("ActionAI.jar")
}