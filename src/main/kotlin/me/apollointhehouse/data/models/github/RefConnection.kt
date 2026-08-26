package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class RefConnection(val nodes: List<RefNode>)