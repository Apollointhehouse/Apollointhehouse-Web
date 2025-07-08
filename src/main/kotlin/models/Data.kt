package me.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val search: Search
)