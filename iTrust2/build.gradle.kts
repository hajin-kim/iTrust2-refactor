plugins {
    java
    id("org.springframework.boot") version "2.7.16"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"

    id("com.diffplug.spotless") version "6.22.0"
}

group = "edu.ncsu.csc"
version = "0.0.1-SNAPSHOT"
description = "iTrust2"

val javaVersion = JavaVersion.VERSION_17

java {
    sourceCompatibility = javaVersion
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-devtools")

    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.hibernate:hibernate-validator:6.1.5.Final")
    runtimeOnly("mysql:mysql-connector-java:8.0.28")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("com.paulhammant:ngwebdriver:1.0")
    implementation("io.github.bonigarcia:webdrivermanager:3.6.2")

    testImplementation("io.cucumber:cucumber-java:6.9.0")
    testImplementation("io.cucumber:cucumber-junit:6.9.0")
    testImplementation("io.cucumber:cucumber-spring:6.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test:5.3.6.RELEASE")
    testImplementation("junit:junit:4.13.1")
}

spotless {
    // optional: limit format enforcement to just the files changed by this feature branch
//    ratchetFrom("origin/main")

    format("misc") {
        target("*.gradle", "*.md", ".gitignore")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
    java {
        googleJavaFormat("1.18.1")
            .aosp()
            .reflowLongStrings()
            .formatJavadoc(true)
            .reorderImports(true)

        formatAnnotations()
        importOrder(group as String, "java|javax|jakarta", "", "\\#$group", "\\#")
    }
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}
