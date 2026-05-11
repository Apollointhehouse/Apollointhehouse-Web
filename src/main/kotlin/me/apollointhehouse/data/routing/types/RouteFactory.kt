package me.apollointhehouse.data.routing.types

import me.apollointhehouse.data.routing.Page
import me.apollointhehouse.data.routing.Router

interface RouteFactory<T: Any> {
    context(_: Router.Builder)
    infix fun T.bind(page: Page): Route
}