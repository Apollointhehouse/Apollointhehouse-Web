package me.apollointhehouse

import me.apollointhehouse.ui.pages.blogs
import me.apollointhehouse.ui.pages.index
import me.apollointhehouse.ui.pages.projects
import me.apollointhehouse.ui.pages.test
import me.apollointhehouse.data.routing
import me.apollointhehouse.data.setupResources

fun main() {
    setupResources()

    routing(
        "/" to { index() },
        "/projects" to { projects() },
        "/blogs" to { blogs() },
        "/test" to { test() },
    )

    println("Done!")
}
