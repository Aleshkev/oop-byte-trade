plugins {
    id("java")
}

group = "pl.edu.mimuw"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

// Allow Unicode characters in identifiers.
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}