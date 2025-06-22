plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "2.1.0"
    id("org.jetbrains.kotlinx.kover") version "0.9.1"
}

group = "br.com.fiap"

version = "0.0.1-SNAPSHOT"

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(kotlin("test"))
    runtimeOnly("org.postgresql:postgresql")
    implementation("jakarta.inject:jakarta.inject-api:2.0.0")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.3.1.RELEASE")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.0")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter:1.17.3")
    testImplementation("org.testcontainers:postgresql:1.17.3")
    testImplementation("io.cucumber:cucumber-java:7.23.0")
    testImplementation("io.cucumber:cucumber-spring:7.23.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.23.0")
    testRuntimeOnly("org.junit.platform:junit-platform-console-standalone:1.10.2")
    implementation("com.vladmihalcea:hibernate-types-60:2.21.1")
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

kover { reports { filters { includes { classes("br.com.fiap.automotivesalesservice.core.*") } } } }

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging { events("skipped", "passed", "failed") }
}

tasks.test {
    useJUnitPlatform()
    testLogging { events("skipped", "passed", "failed") }
}

tasks {
    val runBDDTests by
        registering(Test::class) {
            description = "Run Cucumber BDD tests"
            group = "verification"

            useJUnitPlatform { includeEngines("cucumber") }

            testLogging {
                events("skipped", "passed", "failed")
                showStandardStreams = true
            }

            systemProperty("cucumber.execution.parallel.enabled", true)
            systemProperty("cucumber.execution.parallel.config.strategy", "dynamic")
            systemProperty(
                "cucumber.plugin",
                "pretty, summary, timeline:build/reports/timeline, html:build/reports/cucumber.html",
            )
            systemProperty("cucumber.publish.quiet", true)

            finalizedBy("koverXmlReport", "koverHtmlReport")
        }

    test {
        dependsOn(runBDDTests)
        finalizedBy("koverXmlReport", "koverHtmlReport")
    }

    koverHtmlReport { dependsOn(test, runBDDTests) }

    koverXmlReport { dependsOn(test, runBDDTests) }
}
