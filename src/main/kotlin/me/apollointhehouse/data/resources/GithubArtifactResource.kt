package me.apollointhehouse.data.resources

import kotlinx.coroutines.runBlocking
import me.apollointhehouse.data.API
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.models.github.Artifact
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.writeBytes

class GithubArtifactResource(
    val name: String,
    val artifact: Artifact,
    val savePath: String = "",
    val ext: String = "pdf",
) : Resource {
    override val url: String = artifact.archiveDownloadUrl

    context(config: Config)
    override fun resolve(): ResolvedResource {
        val dir = Path("${config.base}/$savePath/$name.$ext")

        if (!dir.exists()) {
            dir.parent?.createDirectories()
            val bytes = runBlocking { API.downloadArtifactBytes(artifact) }
            dir.writeBytes(bytes)
        }

        return ResolvedResource("$name.$ext", Path("/$savePath/$name.$ext"))
    }
}