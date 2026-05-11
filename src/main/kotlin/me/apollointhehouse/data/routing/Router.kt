package me.apollointhehouse.data.routing

import me.apollointhehouse.data.routing.types.Route

class Router(private val routes: List<Route>) {
    fun create() {
        for (route in routes) with (route) {
            create()
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

inline fun routing(builder: context(Router.Builder) () -> Unit): Router {
    return Router.Builder().apply(builder).build()
}
