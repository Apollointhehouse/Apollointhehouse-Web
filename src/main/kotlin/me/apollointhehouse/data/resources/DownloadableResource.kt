package me.apollointhehouse.data.resources

import me.apollointhehouse.data.Config
import java.net.URI
import java.security.MessageDigest
import kotlin.io.path.createDirectories
import kotlin.io.path.div
import kotlin.io.path.writeBytes

class DownloadableResource(
    val name: String,
    override val url: String,
    val savePath: String = "",
    val ext: String,
    val hashed: Boolean = false,
) : Resource {
    private var cached: ResolvedResource? = null

    context(config: Config)
    override fun resolve(): ResolvedResource = cached ?: download().also { cached = it }

    context(config: Config)
    private fun download(): ResolvedResource {
        val bytes = URI(url).toURL().readBytes()
        val fileName = if (hashed) "$name.${bytes.contentHash()}.$ext" else "$name.$ext"

        val file = (config.base / savePath).createDirectories() / fileName
        file.writeBytes(bytes)

        val publicUrl = listOf(savePath.trim('/'), fileName)
            .filter { it.isNotEmpty() }
            .joinToString(separator = "/", prefix = "/")

        return ResolvedResource(publicUrl, file)
    }

    private fun ByteArray.contentHash(): String =
        MessageDigest.getInstance("SHA-256")
            .digest(this)
            .joinToString("") { "%02x".format(it) }
            .take(10)
}
