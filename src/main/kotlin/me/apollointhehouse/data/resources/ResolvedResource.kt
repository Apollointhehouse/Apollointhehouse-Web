package me.apollointhehouse.data.resources

import me.apollointhehouse.data.Config
import java.nio.file.Path

class ResolvedResource(
    override val url: String,
    val path: Path,
) : Resource {
    context(config: Config)
    override fun resolve(): ResolvedResource = this

    override fun toString() = path.toString()
}