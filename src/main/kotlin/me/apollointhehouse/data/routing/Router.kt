package me.apollointhehouse.data.routing

import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.data.logger
import kotlin.io.path.writer

class Router(private val routes: List<Route>) {
    private val logger = logger()

    fun create() {
        for (route in routes) with (route) {
            logger.info("Creating route: $url")

            create().writer().use {
                it.appendLine("<!DOCTYPE html>").appendHTML().html(block = page)
            }
        }
    }

    class Builder {
        private val routes = mutableListOf<Route>()

        fun build(): Router {
            return Router(routes)
        }

        fun addRoute(route: Route): Builder {
            routes.add(route)
            return this
        }
    }
}
