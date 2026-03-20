package me.apollointhehouse.data

import java.net.URI
import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.writeBytes

object Resources {
    val htmx = download(
        name = "htmx.min",
        url = "https://cdn.jsdelivr.net/npm/htmx.org@2.0.7/dist/htmx.min.js",
        ext = "js"
    )
    val picoCSS = download(
        name = "pico.min",
        url = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css",
        ext = "css"
    )

    fun download(
        name: String,
        url: String,
        path: String = "",
        ext: String
    ): String {
        val dir = Path("${Config.base}/$path/$name.$ext")
        dir.createFile()

        dir.writeBytes(URI(url).toURL().readBytes())

        return "$path/$name.$ext"
    }
}