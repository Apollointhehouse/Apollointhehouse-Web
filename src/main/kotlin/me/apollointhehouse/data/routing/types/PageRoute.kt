package me.apollointhehouse.data.routing.types

import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.html
import kotlinx.html.stream.createHTML
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.Router
import me.apollointhehouse.ui.html.PartialFilter.Companion.filterPartials
import java.nio.file.Path
import kotlin.io.path.*

class PageRoute private constructor(
    private val url: String,
    private val page: Page
) : Route {
    private val logger = logger()

    override fun create() {
        logger.info("Creating route: $url")


        val html = createHTMLDocument()
            .html { page() }
            .serialize()

        val folder = Path.of("${Config.base}/$url")
        if (!folder.exists()) folder.createDirectories()
        (folder / "index.html")
            .also { it.createFile() }
            .writer()
            .use {
                it.append(html)
            }

        logger.info("Creating Partial: /partial$url")
        val partialHtml = createHTML()
            .filterPartials()
            .html { page() }

        val partial = Path.of("${Config.partials}/$url")
        partial.createDirectories()
        (partial / "index.html")
            .also { it.createFile() }
            .writer()
            .use {
                it.append(partialHtml)
            }
    }

    companion object {
        context(builder: Router.Builder)
        infix fun String.bind(page: Page): Route {
            val route = PageRoute(this, page)
            builder.route(route)
            return route
        }
    }
}