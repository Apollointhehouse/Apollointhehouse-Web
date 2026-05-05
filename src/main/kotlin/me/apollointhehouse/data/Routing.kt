package me.apollointhehouse.data

import io.ktor.http.HttpStatusCode
import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import java.io.File

fun routing(vararg routes: Pair<String, HTML.() -> Unit>) {
    for ((route, page) in routes) {
        println("Creating route: $route")

        val folder = File("${Config.base}/$route").also { it.mkdirs() }
        val file = File("$folder/index.html").also { it.createNewFile() }

        file.writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
        }
    }
}

fun errorRouting(vararg routes: Pair<HttpStatusCode, HTML.() -> Unit>) {
    for ((code, page) in routes) {
        println("Creating code route: $code")

        val file = File("${Config.base}/${code.value}.html").also { it.createNewFile() }
        file.writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
        }
    }
}

fun setupResources() {
    File("./src/main/resources/static").copyRecursively(target = Config.base.toFile(), overwrite = true)
}
