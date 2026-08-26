package me.apollointhehouse.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class GraphQLQuery(
    val query: String,
    val variables: JsonObject? = null,
)
