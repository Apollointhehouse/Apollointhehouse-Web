package me.apollointhehouse.data.routing

import java.nio.file.Path

sealed interface Route {
    val url: String

    val page: Page
    fun create(): Path
}