@file:OptIn(ExperimentalPathApi::class)
package me.apollointhehouse.data

import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.deleteRecursively
import kotlin.io.path.div
import kotlin.io.path.exists

object Config {
    val base = Path("./out")
        .also { if (it.exists()) it.deleteRecursively() }
        .createDirectories()

    val partials = (base / "partials")
        .also { if (it.exists()) it.deleteRecursively() }
        .also { it.createDirectories() }
    const val README_STATS_API = "https://github-readme-stats-one-orcin.vercel.app/api"
}

object GitHubConfig {
    val token: String
        get() = System.getenv("PAT") ?: error("Missing PAT!")
}
