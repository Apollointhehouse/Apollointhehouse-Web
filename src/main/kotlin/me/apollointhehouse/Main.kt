package me.apollointhehouse

import me.apollointhehouse.pages.*
import me.apollointhehouse.utils.routing
import me.apollointhehouse.utils.setupResources

fun main(args: Array<String>) {
    Config.pat = requireNotNull(args.firstOrNull()) { "Missing PAT!" }

    setupResources()

    routing(
        "/" to { index() },
        "/projects" to { projects() },
        "/blogs" to { blogs() },
        "/test" to { test() },
    )

    println("Done!")
}
