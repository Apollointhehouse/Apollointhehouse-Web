@file:OptIn(ExperimentalPathApi::class)

package me.apollointhehouse.data.routing

import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.copyToRecursively
import kotlin.io.path.writer

private val logger = logger {}

typealias Page =  HTML.() -> Unit
fun routing(vararg routes: Route) {
    logger.info("Creating routes...")
    for (route in routes) with (route) {
        logger.info("Creating route: $url")

        create().writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
        }
    }
}

fun setupResources() {
    logger.info("Setting up resources...")
    Path("./src/main/resources/static").copyToRecursively(target = Config.base, followLinks = false, overwrite = true)
}