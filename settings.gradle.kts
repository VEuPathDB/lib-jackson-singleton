rootProject.name = "jackson-singleton"

include(":lib:common")
project(":lib:common").name = "jackson-singleton-common"

include(":lib:json")
project(":lib:json").name = "jackson-singleton-json"

include(":lib:yaml")
project(":lib:yaml").name = "jackson-singleton-yaml"
