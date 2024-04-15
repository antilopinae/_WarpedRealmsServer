group = "adapters"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(project(":adapters:grpc"))
}

tasks.test {
    useJUnitPlatform()
}
