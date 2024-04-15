import com.google.protobuf.gradle.*

val ktorVersion: String by project
val grpcKotlinVersion: String by project
val grpcVersion: String by project
val protobufVersion: String by project
val protobufPluginVersion: String by project
val kotlinxCoroutinesVersion: String by project

group = "adapters:grpc"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")

    //serialization
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")

    //grpc
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")

    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")

    implementation("io.grpc:grpc-netty:$grpcVersion")
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")

//    implementation("io.grpc:protoc-gen-grpc-java:$grpcVersion")
//    implementation("io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            if (it.name.startsWith("generateTestProto")) {
                it.dependsOn("jar")
            }
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
