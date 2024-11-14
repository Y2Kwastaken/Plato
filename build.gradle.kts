plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.annotations)
    implementation(libs.flatlaf)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.test {
    useJUnitPlatform()
}
