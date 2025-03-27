package me.apollointhehouse

import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import java.io.File
import kotlin.io.path.Path
import me.apollointhehouse.pages.index
import me.apollointhehouse.pages.projects
import kotlin.io.path.createDirectories

fun main() {
    val base = Path("./out").createDirectories()

    for ((route, page) in routes) {
        println("Creating $route page")

        val file = File("$base/$route.html")
        file.createNewFile()

        file.writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
        }
    }

    File("./src/main/resources").copyRecursively(target = base.toFile(), overwrite = true)

    println("Done!")
}

val routes = mapOf<String, HTML.() -> Unit>(
    "index" to { index() },
    "projects" to { projects() },
)