package me.apollointhehouse.data.routing.types

import me.apollointhehouse.data.routing.Page

sealed interface Route {
    val url: String

    val page: Page
    fun create()
}