val jwtVersion: String by project
val ktorVersion: String by project
val exposedVersion: String by project
val hikariVersion: String by project
val mysqlVersion: String by project

group = "adapters:rest-api"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    //test dep
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    //jjwt
    implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
    implementation("io.jsonwebtoken:jjwt-impl:$jwtVersion")
    implementation("io.jsonwebtoken:jjwt-jackson:$jwtVersion")

    //core
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")

    implementation("io.ktor:ktor-server-freemarker:$ktorVersion")

    //exposed orm
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    //database dep
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("mysql:mysql-connector-java:$mysqlVersion")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.2.0")

    //authentication
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation("io.ktor:ktor-server-sessions-jvm")

    //serialization
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
}

tasks.test {
    useJUnitPlatform()
}
