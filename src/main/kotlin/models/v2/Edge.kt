package models.v2


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Edge(
    @SerialName("node")
    val node: Repo
)