package models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PinnedItems(
    @SerialName("nodes")
    val nodes: List<Node>
)