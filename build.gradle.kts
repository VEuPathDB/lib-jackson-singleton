plugins {
  `java-library`
  `maven-publish`
  kotlin("jvm") version "1.6.10"
}

group = "org.veupathdb.lib"
version = "3.1.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))

  api("com.fasterxml.jackson.core:jackson-databind:2.14.2")
  api("com.fasterxml.jackson.datatype:jackson-datatype-json-org:2.14.2")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")
  api("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
  api("com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.2")
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

java {
  withSourcesJar()
  withJavadocJar()
}

publishing {
  repositories {
    maven {
      name = "GitHub"
      url  = uri("https://maven.pkg.github.com/veupathdb/lib-jackson-singleton")
      credentials {
        username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
        password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
      }
    }
  }

  publications {
    create<MavenPublication>("gpr") {
      from(components["java"])
      pom {
        name.set("JaxRS Container Core Library")
        description.set("Provides base methods, endpoints, server setup, and utilities for use in containerized VEuPathDB JaxRS based applications.")
        url.set("https://github.com/VEuPathDB/lib-jackson-singleton")
        developers {
          developer {
            id.set("epharper")
            name.set("Elizabeth Paige Harper")
            email.set("epharper@upenn.edu")
            url.set("https://github.com/foxcapades")
            organization.set("VEuPathDB")
          }
        }
        scm {
          connection.set("scm:git:git://github.com/VEuPathDB/lib-jackson-singleton.git")
          developerConnection.set("scm:git:ssh://github.com/VEuPathDB/lib-jackson-singleton.git")
          url.set("https://github.com/VEuPathDB/lib-jackson-singleton")
        }
      }
    }
  }
}
