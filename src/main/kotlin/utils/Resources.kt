package me.apollointhehouse.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.apollointhehouse.Config
import java.io.File
import java.net.URI

object Resources {
    private val scope = CoroutineScope(Dispatchers.IO)

    val htmx = download("htmx.min.js", "https://cdn.jsdelivr.net/npm/htmx.org@2.0.7/dist/htmx.min.js")
    val picoCSS = download("pico.min.css", "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css")

    private fun download(name: String, url: String): String {
        val dir = File("${Config.base}/$name")
        dir.createNewFile()

        dir.writeText(URI(url).toURL().readText())

        return "/$name"
    }

    fun imgDownload(name: String, url: String, ext: String): String {
        scope.launch {
            val dir = File("${Config.base}/assets/images/$name.$ext")
            dir.createNewFile()

            dir.writeBytes(URI(url).toURL().readBytes())
        }

        return "/assets/images/$name.$ext"
    }
}