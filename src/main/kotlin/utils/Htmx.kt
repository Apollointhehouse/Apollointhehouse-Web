package me.apollointhehouse.utils

import me.apollointhehouse.Config
import java.io.File
import java.net.URI

val htmx: String by lazy {
    val dir = File("${Config.base}/htmx.min.js")
    dir.createNewFile()

    dir.writeText(URI("https://cdn.jsdelivr.net/npm/htmx.org@2.0.7/dist/htmx.min.js").toURL().readText())

    "htmx.min.js"
}