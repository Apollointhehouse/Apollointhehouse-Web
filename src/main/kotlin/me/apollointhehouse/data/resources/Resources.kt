package me.apollointhehouse.data.resources

import kotlin.io.path.Path

class Resources {
    val fixi = ResolvedResource(
        url = "/fixi-0.9.4.js",
        path = Path("./src/main/resources/static/fixi-0.9.4.js")
    )

    val extFixi = ResolvedResource(
        url = "/ext-fixi.js",
        path = Path("./src/main/resources/static/ext-fixi.js")
    )

    val picoCSS = download(
        name = "pico.min",
        url = "https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css",
        path = "assets/css",
        ext = "css",
        hashed = true
    )

    val styleCSS = ResolvedResource(
        url = "/style.min.css",
        path = Path("./src/main/resources/styles/style.min.css")
    )

    private fun download(
        name: String,
        url: String,
        path: String = "",
        ext: String,
        hashed: Boolean = false
    ): Resource = DownloadableResource(name, url, path, ext, hashed)
}

