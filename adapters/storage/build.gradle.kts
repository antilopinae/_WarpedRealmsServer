val exposedVersion: String by project

group = "adapters:storage"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //ORM
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    //database
    implementation("org.mariadb.jdbc:mariadb-java-client:3.2.0")
}

tasks.test {
    useJUnitPlatform()
}
