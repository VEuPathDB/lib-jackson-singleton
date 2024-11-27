dependencies {
  implementation(project(":lib:jackson-singleton-common"))

  api(platform(libs.jackson))
  api("com.fasterxml.jackson.core:jackson-databind")
  api("com.fasterxml.jackson.datatype:jackson-datatype-json-org")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
  api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
  api("com.fasterxml.jackson.module:jackson-module-kotlin")
  api("com.fasterxml.jackson.module:jackson-module-parameter-names")
}
