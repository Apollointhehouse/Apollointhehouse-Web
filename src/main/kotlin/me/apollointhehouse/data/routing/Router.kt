package me.apollointhehouse.data.routing

import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.resources.Resources
import me.apollointhehouse.data.routing.types.Route

class Router(private val routes: List<Route>) {
    private val logger = logger()

    context( _: Config)
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

inline fun routing(builder: context(Router.Builder, Config, Resources) () -> Unit) = context(Config(), Resources()) {
    Router.Builder().apply {
        builder()
    }.build().create()
}
