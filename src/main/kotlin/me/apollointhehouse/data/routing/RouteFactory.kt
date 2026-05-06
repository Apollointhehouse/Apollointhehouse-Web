package me.apollointhehouse.data.routing

interface RouteFactory<T: Any> {
    context(_: Router.Builder)
    infix fun T.bind(page: Page): Route
}