package me.apollointhehouse

import kotlin.io.path.Path
import kotlin.io.path.createDirectories

object Config {
    var pat: String = ""
    val base = Path("./out").createDirectories()
    val readmeStatsAPI = "https://github-readme-stats-one-orcin.vercel.app/api"
}
