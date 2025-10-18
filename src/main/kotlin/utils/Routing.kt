package me.apollointhehouse.utils

import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.Config
import java.io.File

fun routing(vararg routes: Pair<String, HTML.() -> Unit>) {
    for ((route, page) in routes) {
        println("Creating route: $route")

        val folder =  File("${Config.base}/$route").also { it.mkdirs() }
        val file = File("$folder/index.html").also { it.createNewFile() }

        file.writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
        }
    }
}

fun setupResources() {
    File("./src/main/resources/static").copyRecursively(target = Config.base.toFile(), overwrite = true)
}