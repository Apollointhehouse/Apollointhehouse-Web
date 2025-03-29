package me.apollointhehouse

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.pages.*
import java.io.File
import kotlin.io.path.*

val base = Path("./out").createDirectories()

fun main(args: Array<String>) {
    Config.pat = args.getOrNull(0) ?: error("Missing PAT!")


    for ((route, page) in routes) {
        println("Creating route: $route")

        val folder =  File("$base/$route").also { it.mkdirs() }
        val file = File("$folder/index.html").also { it.createNewFile() }

        file.writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
        }
    }

    File("./src/main/resources").copyRecursively(target = base.toFile(), overwrite = true)

    println("Done!")
}

val routes = mapOf<String, HTML.() -> Unit>(
    "/" to { index() },
    "projects" to { projects() },
    "blogs" to { blogs() },
)