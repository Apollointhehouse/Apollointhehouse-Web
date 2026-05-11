package me.apollointhehouse.data.routing

import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.types.Route

class Router(private val routes: List<Route>) {
    private val logger = logger()

    fun create() {
        logger.info("Generating Static Pages...")
        for (route in routes) route.create()
        logger.info("Done!")
    }

    class Builder {
        private val routes = mutableListOf<Route>()

        fun build(): Router = Router(routes)

        fun route(route: Route): Builder {
            routes.add(route)
            return this
        }
    }
}

inline fun routing(builder: context(Router.Builder) () -> Unit): Router =
    Router.Builder().apply(builder).build()
