package me.apollointhehouse.data.routing.types

import io.ktor.http.*
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.Page
import me.apollointhehouse.data.routing.Router
import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.writer

class StatusRoute private constructor(
    val code: HttpStatusCode,
    override val url: String,
    override val page: Page
) : Route {
    private val logger = logger()

    override fun create() {
        logger.info("Creating Status Route: $url")
        Path.of("${Config.base}/${code.value}.html")
            .also { it.createFile() }
            .writer()
            .use {
                it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
            }
    }

    companion object : RouteFactory<HttpStatusCode> {
        context(builder: Router.Builder)
        override infix fun HttpStatusCode.bind(page: Page): StatusRoute {
            val route = StatusRoute(this, this.value.toString(), page)
            builder.addRoute(route)
            return route
        }
    }
}