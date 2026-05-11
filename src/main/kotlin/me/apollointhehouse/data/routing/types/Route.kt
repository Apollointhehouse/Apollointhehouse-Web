package me.apollointhehouse.data.routing.types

import kotlinx.html.HTML

sealed interface Route {
    fun create()
}

typealias Page = HTML.() -> Unit