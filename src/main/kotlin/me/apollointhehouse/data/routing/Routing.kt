@file:OptIn(ExperimentalPathApi::class)

package me.apollointhehouse.data.routing

import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.data.Config
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.copyToRecursively
import kotlin.io.path.writer

typealias Page =  HTML.() -> Unit
fun routing(vararg routes: Route) {
    for (route in routes) with (route) {
        println("Creating route: $url")

        create().writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
        }
    }
}

fun setupResources() {
    Path("./src/main/resources/static").copyToRecursively(target = Config.base, followLinks = false, overwrite = true)
}