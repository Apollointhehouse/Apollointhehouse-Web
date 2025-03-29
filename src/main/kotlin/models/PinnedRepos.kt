package models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PinnedRepos(
    @SerialName("data")
    val `data`: Data
)