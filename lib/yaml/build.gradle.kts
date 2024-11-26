dependencies {
  implementation(project(":lib:jackson-singleton-common"))
  api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")

  testImplementation(libs.test.junit)
}

tasks.test {
  useJUnitPlatform()
}
