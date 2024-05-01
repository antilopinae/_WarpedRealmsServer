val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val exposedVersion: String by project
val koinVersion: String by project
val hikariVersion: String by project
val mysqlVersion: String by project
//val serialize_version: String by project

group = "adapters"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(project(":adapters:grpc"))
    implementation(project(":adapters:rest"))
    implementation(project(":adapters:storage"))

    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-host-common")
    implementation("io.ktor:ktor-server-websockets")
    implementation("io.ktor:ktor-server-netty")
    testImplementation("io.ktor:ktor-server-tests")
    //implementation("io.ktor:ktor-server-thymeleaf-jvm")
//    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-config-yaml:2.3.9")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

//    serialize
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    //jvm надо вырезать если не работает

    //kotlinx.serialization - работает быстрее

//    Exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

//    Database Connector
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("mysql:mysql-connector-java:$mysqlVersion")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.2.0")
//Authentication
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation("io.ktor:ktor-server-sessions-jvm")


//    Hikari - легкий и быстрый фреймворк для пулов соединений JDBC.
//    несколько соединений с бд

//    DI Koin
//    implementation("io.insert-koin:koin-core:$koin_version")
//    implementation("io.insert-koin:koin-ktor:$koin_version")


    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
}
apply(plugin = "io.ktor.plugin")

tasks.test {
    useJUnitPlatform()
}

ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
    repositories {
        mavenCentral()
        maven("https://mvnrepository.com/artifact/io.ktor/ktor-bom")
    }
//  docker {
//    jreVersion.set(JavaVersion.VERSION_17)
//    localImageName.set("sample-docker-image")
//    imageTag.set("0.0.1-preview")
//    portMappings.set(listOf(
//      io.ktor.plugin.features.DockerPortMapping(
//        80,
//        8080,
//        io.ktor.plugin.features.DockerPortMappingProtocol.TCP
//      )
//    ))
//
//    externalRegistry.set(
//      io.ktor.plugin.features.DockerImageRegistry.dockerHub(
//        appName = provider { "ktor-app" },
//        username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
//        password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
//      )
//    )
//  }
}
