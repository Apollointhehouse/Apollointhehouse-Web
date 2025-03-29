package me.apollointhehouse

import kotlin.io.path.Path
import kotlin.io.path.createDirectories

object Config {
    var pat: String = ""
    val base = Path("./out").createDirectories()
}
