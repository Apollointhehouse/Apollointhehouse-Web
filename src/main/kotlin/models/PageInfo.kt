package me.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val endCursor: String,
    val hasNextPage: Boolean
)