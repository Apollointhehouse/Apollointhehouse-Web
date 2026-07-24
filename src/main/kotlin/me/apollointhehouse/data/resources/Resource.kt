package me.apollointhehouse.data.resources

import me.apollointhehouse.data.Config

interface Resource {
    val url: String

    context(config: Config)
    fun resolve(): ResolvedResource
}