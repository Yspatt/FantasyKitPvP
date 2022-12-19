import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import javax.tools.ToolProvider

plugins {
    kotlin("jvm") version "1.3.10"
    id("com.github.johnrengelman.shadow") version "2.0.3"
    id("net.minecrell.plugin-yml.bukkit") version "0.2.1"

}

group = "fantasynetwork.fantasykitpvp"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenLocal()

    // spigot
    maven {
        name = "spigot"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    // citizens
    maven{
        name="citizens"
        url = uri("http://repo.citizensnpcs.co/")
    }
    //vault
    maven{
        name="vault"
        url = uri("http://nexus.hc.to/content/repositories/pub_releases")
    }

    // exposed
    maven {
        name = "exposed"
        url = uri("https://dl.bintray.com/kotlin/exposed")
    }

}

dependencies {

    
    compile(kotlin("stdlib"))
    compile(kotlin("reflect"))

    compile("org.jetbrains.exposed:exposed:0.11.2")
    compileOnly("fr.minuskube.inv:smart-invs:1.2.7")
    compileOnly("net.milkbowl.vault:VaultAPI:1.7")

    // dependecias nao compiladas dentro
    compileOnly(files(File(projectDir, "libs/spigot-1.8.8.jar")))
    compileOnly(files(File(projectDir, "libs/citizens.jar")))
   // compileOnly(files(File(projectDir,"libs/holographicdisplays.jar")))

}

tasks {
    "compileKotlin"(KotlinCompile::class) {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    "shadowJar"(ShadowJar::class) {
        baseName = project.name
        classifier = ""
    }
   
    tasks.withType<JavaCompile>{
        options.encoding = "UTF-8"
    }

}

// plugin.yml
bukkit {
    name = "FantasyKitPvP"
    version = project.version.toString()
    main = "fantasynetwork.fantasykitpvp.FantasyKitPvP"

    authors = listOf("Yspatt", "Mboscolo")
}