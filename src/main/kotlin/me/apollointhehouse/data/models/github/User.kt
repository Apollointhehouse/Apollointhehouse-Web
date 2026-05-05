package me.apollointhehouse.data.models.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("repositories")
    val repositories: Repositories,
)
