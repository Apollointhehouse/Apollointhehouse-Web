package me.apollointhehouse.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoInfo(
    val description: String? = null,
    val name: String,
    val url: String,
    @SerialName("object")
    val readme: Readme? = null,
)