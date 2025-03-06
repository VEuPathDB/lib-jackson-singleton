import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `maven-publish`
  kotlin("jvm") version "2.1.10"
  id("org.jetbrains.dokka") version "2.0.0"
}

group = "org.veupathdb.lib"
version = "4.0.4"

allprojects {
  repositories {
    mavenCentral()
  }
}

configure(listOf(
  project(":lib:jackson-singleton-common"),
  project(":lib:jackson-singleton-json"),
  project(":lib:jackson-singleton-yaml"),
)) {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.dokka")
  apply(plugin = "maven-publish")

  group = rootProject.group
  version = rootProject.version

  kotlin {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_21)
      optIn = listOf("kotlin.contracts.ExperimentalContracts")
    }
  }

  java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withSourcesJar()
    withJavadocJar()
  }

  tasks.dokkaHtml {
    val featVersion = (rootProject.version as String).substring(0, (rootProject.version as String).lastIndexOf('.')) + ".0"
    outputDirectory.set(file("${rootDir}/docs/dokka/${this@configure.name}/v${featVersion}"))
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
        groupId = rootProject.group.toString()
        version = rootProject.version.toString()

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
}

kotlin {
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_21)
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}
