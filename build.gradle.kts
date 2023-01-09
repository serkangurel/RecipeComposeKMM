plugins {
    //trick: for the same plugin versions in all sub-modules
    val kotlinVersion = Versions.kotlinVersion
    val agpVersion = Versions.agpVersion

    id("com.android.application") version agpVersion apply false
    id("com.android.library") version agpVersion apply false
    kotlin("android") version kotlinVersion apply false
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
    kotlin("multiplatform") version kotlinVersion apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
