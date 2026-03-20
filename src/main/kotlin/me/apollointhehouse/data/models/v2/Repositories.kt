package models.v2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repositories(
    @SerialName("edges")
    val edges: List<Edge>,
)
