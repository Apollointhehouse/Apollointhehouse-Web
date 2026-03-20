package me.apollointhehouse.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLQuery(
    val query: String,
)
