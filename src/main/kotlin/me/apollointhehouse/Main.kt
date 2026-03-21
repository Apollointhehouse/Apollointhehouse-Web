package me.apollointhehouse

import me.apollointhehouse.ui.pages.blogs
import me.apollointhehouse.ui.pages.index
import me.apollointhehouse.ui.pages.projects
import me.apollointhehouse.ui.pages.test
import me.apollointhehouse.data.routing
import me.apollointhehouse.data.setupResources
import me.apollointhehouse.ui.pages.CV

fun main() {
    setupResources()

    routing(
        "/" to { index() },
        "/projects" to { projects() },
        "/blogs" to { blogs() },
        "/test" to { test() },
        "/CV" to { CV() },
    )

    println("Done!")
}
