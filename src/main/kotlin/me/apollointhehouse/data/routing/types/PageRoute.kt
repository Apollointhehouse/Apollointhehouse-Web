package me.apollointhehouse.data.routing.types

import kotlinx.html.html
import kotlinx.html.stream.createHTML
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.Router
import java.nio.file.Path
import kotlin.io.path.*

class PageRoute private constructor(
    private val url: String,
    private val page: Page
) : Route {
    private val logger = logger()

    override fun create() {
        logger.info("Creating route: $url")
        val html = createHTML()
            .html(block = page)

        val folder = Path.of("${Config.base}/$url")
        if (!folder.exists()) folder.createDirectories()
        (folder / "index.html")
            .also { it.createFile() }
            .writer()
            .use {
                it.appendLine("<!DOCTYPE html>").append(html)
            }

        logger.info("Creating Partial: /partial$url")
        val partial = Path.of("${Config.partials}/$url")
        partial.createDirectories()
        (partial / "index.html")
            .also { it.createFile() }
            .writer()
            .use {
                val partialHtml = html
                    .substringAfter("<main class=\"container\" style=\"min-height: 100vh;\">")
                    .substringBefore("</main>")

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