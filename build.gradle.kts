plugins {
  `maven-publish`
  kotlin("jvm") version "2.0.20"
  id("org.jetbrains.dokka") version "1.9.20"
}

group = "org.veupathdb.lib"
version = "3.2.1"

repositories {
  mavenCentral()
}

dependencies {
  api(platform("com.fasterxml.jackson:jackson-bom:2.17.2"))
  api("com.fasterxml.jackson.core:jackson-databind")
  api("com.fasterxml.jackson.datatype:jackson-datatype-json-org")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
  api("com.fasterxml.jackson.module:jackson-module-kotlin")
  api("com.fasterxml.jackson.module:jackson-module-parameter-names")
}

java {
  withSourcesJar()
  withJavadocJar()
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
    vendor.set(JvmVendorSpec.AMAZON)
  }

  compilerOptions {
    optIn = listOf("kotlin.contracts.ExperimentalContracts")
  }
}

tasks.dokkaHtml {
  val featVersion = (version as String).substring(0, (version as String).lastIndexOf('.')) + ".0"
  outputDirectory.set(file("docs/dokka/v$featVersion"))
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
            email.set("foxcapades.io@gmail.com")
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
