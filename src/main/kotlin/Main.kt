package me.apollointhehouse

import me.apollointhehouse.pages.*

fun main(args: Array<String>) {
    Config.pat = args.getOrNull(0) ?: error("Missing PAT!")

    setupResources()

    setupRoutes(mapOf(
        "/" to { index() },
        "/projects" to { projects() },
        "/blogs" to { blogs() },
        "/test" to { test() },
        "/rlhs" to { rlhs() },
    ))

    println("Done!")
}