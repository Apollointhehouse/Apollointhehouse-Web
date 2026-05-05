package me.apollointhehouse.data

import kotlin.io.path.Path
import kotlin.io.path.createDirectories

object Config {
    val base = Path("./out").createDirectories()
    const val README_STATS_API = "https://github-readme-stats-one-orcin.vercel.app/api"
}

object GitHubConfig {
    val token: String
        get() = System.getenv("PAT") ?: error("Missing PAT!")
}
