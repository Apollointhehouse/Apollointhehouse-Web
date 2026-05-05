package me.apollointhehouse.data.models.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryTopics(
    @SerialName("nodes")
    val nodes: List<TopicHolder>,
)
