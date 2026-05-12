package me.apollointhehouse.data.routing.types

import kotlinx.html.HTML
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.html
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.Router
import java.nio.file.Path
import kotlin.io.path.*

class PageRoute private constructor(
    private val url: String,
    private val page: HTML.() -> Unit
) : Route {
    private val logger = logger()

    override fun create() {
        logger.info("Creating Route: $url")

        val html = createHTMLDocument()
            .html { page() }
            .serialize()

        val folder = Path.of("${Config.base}/$url")
        if (!folder.exists()) folder.createDirectories()
        (folder / "index.html")
            .also { it.createFile() }
            .writer()
            .use { it.append(html) }
    }

    companion object {
        context(builder: Router.Builder)
        infix fun String.bind(page: HTML.() -> Unit) {
            builder.route(PageRoute(this, page))
        }
    }
}