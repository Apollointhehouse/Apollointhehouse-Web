package me.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class RepoHolder(
    val repo: RepoInfo
)
