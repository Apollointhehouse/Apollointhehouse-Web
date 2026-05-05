package me.apollointhehouse

import me.apollointhehouse.ui.pages.blogs
import me.apollointhehouse.ui.pages.index
import me.apollointhehouse.ui.pages.projects
import me.apollointhehouse.data.routing
import me.apollointhehouse.data.setupResources
import me.apollointhehouse.ui.pages.CV
import me.apollointhehouse.ui.components.redirect

fun main() {
    setupResources()

    routing(
        "/" to { index() },
        "/projects" to { projects() },
        "/blogs" to { blogs() },
        "/CV" to { CV() },
        "/test" to { redirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ") },
    )

    println("Done!")
}
