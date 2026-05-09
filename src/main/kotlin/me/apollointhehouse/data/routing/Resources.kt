@file:OptIn(ExperimentalPathApi::class)

package me.apollointhehouse.data.routing

import kotlinx.html.HTML
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.copyToRecursively

private val logger = logger {}

typealias Page =  HTML.() -> Unit

fun setupResources() {
    logger.info("Setting up resources...")
    Path("./src/main/resources/static").copyToRecursively(target = Config.base, followLinks = false, overwrite = true)
}