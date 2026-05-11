package me.apollointhehouse.data.routing.types

import io.ktor.http.*
import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.Router
import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.writer

class StatusRoute private constructor(
    val code: HttpStatusCode,
    private val url: String,
    private val content: HTML.() -> Unit
) : Route {
    private val logger = logger()

    override fun create() {
        logger.info("Creating Status Route: $url")
        Path.of("${Config.base}/${code.value}.html")
            .also { it.createFile() }
            .writer()
            .use {
                it.appendLine("<!DOCTYPE html>").appendHTML().html(block = content)
            }
    }

    companion object {
        context(builder: Router.Builder)
        fun status(code: HttpStatusCode, content: HTML.() -> Unit): StatusRoute {
            val route = StatusRoute(code, code.value.toString(), content)
            builder.route(route)
            return route
        }
    }
}