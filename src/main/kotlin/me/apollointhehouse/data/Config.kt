package me.apollointhehouse.data

import kotlin.io.path.Path
import kotlin.io.path.createDirectories

object Config {
    val pat: String = System.getenv("PAT") ?: error("Missing PAT!")
    val base = Path("./out").createDirectories()
    const val README_STATS_API = "https://github-readme-stats-one-orcin.vercel.app/api"
}