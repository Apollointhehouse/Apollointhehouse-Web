package me.apollointhehouse.data.models.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artifact(
    val id: Long,
    val name: String,
    @SerialName("size_in_bytes") val sizeInBytes: Long,
    @SerialName("archive_download_url") val archiveDownloadUrl: String,
    @SerialName("created_at") val createdAt: String,
    val expired: Boolean,
)