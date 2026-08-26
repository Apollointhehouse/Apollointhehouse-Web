package me.apollointhehouse.data.models.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtifactsResponse(
    @SerialName("total_count") val totalCount: Int,
    val artifacts: List<Artifact>,
)