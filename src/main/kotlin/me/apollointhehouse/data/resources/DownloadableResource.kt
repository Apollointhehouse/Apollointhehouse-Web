package me.apollointhehouse.data.resources

import me.apollointhehouse.data.Config
import java.net.URI
import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.writeBytes

class DownloadableResource(
    val name: String,
    override val url: String,
    val savePath: String = "",
    val ext: String
) : Resource {
    context(config: Config)
    override fun resolve(): ResolvedResource {
        val dir = Path("${config.base}/$savePath/$name.$ext")
        if (!dir.exists()) dir.createFile()

        dir.writeBytes(URI(url).toURL().readBytes())

        return ResolvedResource("$savePath/$name.$ext", dir)
    }
}