val jwtVersion: String by project
val ktorVersion: String by project

group = "adapters:rest-api"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //jwt
//    implementation("io.jsonwebtoken:jjwt-api:jwtVersion")
//    implementation("io.jsonwebtoken:jjwt-impl:jwtVersion")
//    implementation("io.jsonwebtoken:jjwt-jackson:jwtVersion")

//    //websocket
//    implementation("io.ktor:ktor-server-websockets-jvm:$ktorVersion")
}

tasks.test {
    useJUnitPlatform()
}
