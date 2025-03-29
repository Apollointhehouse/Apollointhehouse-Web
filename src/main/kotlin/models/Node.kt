package models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Node(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String?,
    @SerialName("url") val url: String,
)