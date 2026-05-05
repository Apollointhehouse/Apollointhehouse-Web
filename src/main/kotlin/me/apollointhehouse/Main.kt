package me.apollointhehouse

import io.ktor.http.HttpStatusCode
import me.apollointhehouse.data.errorRouting
import me.apollointhehouse.data.routing
import me.apollointhehouse.data.setupResources
import me.apollointhehouse.ui.pages.CV
import me.apollointhehouse.ui.pages.NotFound
import me.apollointhehouse.ui.pages.blogs
import me.apollointhehouse.ui.pages.index
import me.apollointhehouse.ui.pages.projects

fun main() {
    setupResources()

    routing(
        "/" to { index() },
        "/projects" to { projects() },
        "/blogs" to { blogs() },
        "/CV" to { CV() },
    )

    errorRouting(
        HttpStatusCode.NotFound to { NotFound() },
    )

    println("Done!")
}
