package me.apollointhehouse.data.routing.types

import me.apollointhehouse.data.Config

sealed interface Route {
    context(config: Config)
    fun create()
}