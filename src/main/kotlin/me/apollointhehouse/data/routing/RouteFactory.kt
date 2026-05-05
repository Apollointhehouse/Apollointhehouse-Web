package me.apollointhehouse.data.routing

interface RouteFactory<T: Any> {
    infix fun T.bind(page: Page): Route
}