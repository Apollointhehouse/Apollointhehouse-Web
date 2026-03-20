package models.v2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicHolder(
    @SerialName("topic")
    val topic: Topic,
    @SerialName("url")
    val url: String?,
)
