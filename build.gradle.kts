val kotlin_version: String by project
val slf4j_version: String by project
val javalin_version: String by project
val kmongo_version: String by project
val fuel_version: String by project

plugins {
    application
    jacoco
    kotlin("jvm") version "1.4.10"
    id("io.gitlab.arturbosch.detekt") version "1.7.0"
    id("com.google.cloud.tools.jib") version "2.2.0"
}

group = "fr.slickteam.gitlabciittests"
version = "0.0.1-SNAPSHOT"

application {
    mainClassName = "fr.slickteam.gitlabciittests.ApplicationKt"
}

sourceSets {
    main {
        resources {
            srcDir("resources")
        }
    }
    test {
        resources {
            srcDir("resources")
        }
    }
}


repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.javalin:javalin:$javalin_version")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.9")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    implementation("com.natpryce:konfig:1.6.10.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.1")
    testImplementation("com.github.kittinunf.fuel:fuel:$fuel_version")
    testImplementation("com.github.kittinunf.fuel:fuel-jackson:$fuel_version")
    testImplementation("io.strikt:strikt-core:0.23.6")
    testImplementation("io.mockk:mockk:1.9.3")
}

tasks {

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<io.gitlab.arturbosch.detekt.Detekt> {
        // Target version of the generated JVM bytecode. It is used for type resolution.
        this.jvmTarget = "1.8"
    }

    withType<Test> {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    withType<Jar> {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }

        from(configurations.runtime.get().map { if (it.isDirectory) it else zipTree(it) })
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
            html.destination = file("${buildDir}/jacocoHtml")
            xml.destination = file("${buildDir}/reports/jacoco.xml")
        }
        executionData.setFrom(fileTree(buildDir).include("/jacoco/test.exec"))
    }

}

detekt {
    failFast = true
    buildUponDefaultConfig = true
    config = files("$projectDir/conf/detekt-config.yml")
}

jib {
    from {
        image = "openjdk:8-jre-alpine"
    }
    container {
        workingDirectory = "/usr/share/javalin/"
        ports = listOf("7000")
    }
}

jacoco {
    toolVersion = "0.8.5"
}