package models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("pinnedItems")
    val pinnedItems: PinnedItems
)