package me.apollointhehouse.data.models.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("user")
    val user: User,
)
