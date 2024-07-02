import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "1.9.23"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
kotlin {
    jvmToolchain(21)
}

tasks {
    jar {
        enabled = false
    }
    shadowJar {
        archiveFileName = "bingo-plugin.jar"
    }
    runServer {
        serverJar(File("run/paper-1.20.6-147.jar"))
        minecraftVersion("1.20.6")
    }
}

bukkit {
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.balduvian.minecraftbingo.BingoPlugin"
    apiVersion = "1.20"
    authors = listOf("balduvian")
}