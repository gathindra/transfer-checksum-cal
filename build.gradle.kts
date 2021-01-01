import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    id("org.jetbrains.compose") version "0.2.0-build132"
}

group = "com.gathi"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    testImplementation(kotlin("test-junit"))
    implementation(compose.desktop.currentOs)
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "14"
}

compose.desktop {
    application {
        // You need java 14 or later for native distributions.
        // Make sure to install java 14 and set JAVA_HOME to 14
        javaHome = System.getenv("JAVA_HOME")
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
            packageName = "transfer-checksum-cal"
            version = "0.1.1"
            description = "Compose for Desktop Example App"
            copyright = "© 2020 Gathindra Gamamadagedon. All rights reserved."
            vendor = "Gathi Soft"
        }
    }
}