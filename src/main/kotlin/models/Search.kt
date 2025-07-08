package me.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class Search(
    val pageInfo: PageInfo,
    val repos: List<RepoHolder>
)
