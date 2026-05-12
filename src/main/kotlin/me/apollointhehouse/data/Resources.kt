package me.apollointhehouse.data

import java.net.URI
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.writeBytes

object Resources {
    val htmx = download(
        name = "htmx.min",
        url = "https://cdn.jsdelivr.net/npm/htmx.org@2.0.7/dist/htmx.min.js",
        ext = "js"
    )

    val htmxPreload = download(
        name = "htmx-ext-preload",
        url = "https://cdn.jsdelivr.net/npm/htmx-ext-preload@2.1.2",
        ext = "js"
    )

    val picoCSS = download(
        name = "pico.min",
        url = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css",
        ext = "css"
    )

    val styleCSS = Resource(
        url = "/style.min.css",
        path = Path("./src/main/resources/styles/style.min.css")
    )

    private fun download(
        name: String,
        url: String,
        path: String = "",
        ext: String
    ): Resource {
        val dir = Path("${Config.base}/$path/$name.$ext")
        if (!dir.exists()) dir.createFile()

        dir.writeBytes(URI(url).toURL().readBytes())

        return Resource("$path/$name.$ext", dir)
    }
}

class Resource(
    val url: String,
    val path: Path,
)