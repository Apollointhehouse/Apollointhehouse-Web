package me.apollointhehouse

import kotlinx.html.*
import me.apollointhehouse.pages.*
import kotlinx.html.stream.appendHTML
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createDirectories

fun main(args: Array<String>) {
    Config.pat = args.getOrNull(0) ?: error("Missing PAT!")

    val base = Path("./out").createDirectories()

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