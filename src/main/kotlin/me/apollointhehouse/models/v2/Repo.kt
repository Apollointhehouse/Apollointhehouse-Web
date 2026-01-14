package models.v2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String?,
    @SerialName("url")
    val url: String,
    @SerialName("stargazerCount")
    val stargazerCount: Int,
    @SerialName("repositoryTopics")
    val repositoryTopics: RepositoryTopics,
)
