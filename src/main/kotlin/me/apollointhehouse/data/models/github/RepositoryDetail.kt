package me.apollointhehouse.data.models.github

import kotlinx.serialization.Serializable

@Serializable
data class RepositoryDetail(
    val name: String,
    val isPrivate: Boolean,
    val refs: RefConnection,
)