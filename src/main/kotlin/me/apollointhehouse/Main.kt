package me.apollointhehouse

import io.ktor.http.*
import me.apollointhehouse.data.API
import me.apollointhehouse.data.blogs.loadBlogPosts
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.ErrorRoute.Companion.bind
import me.apollointhehouse.data.routing.PageRoute.Companion.bind
import me.apollointhehouse.data.routing.Route
import me.apollointhehouse.data.routing.routing
import me.apollointhehouse.data.routing.setupResources
import me.apollointhehouse.ui.components.blog
import me.apollointhehouse.ui.pages.*

private val logger = logger {}

fun main() {
    logger.info("Generating Static Pages...")

    setupResources()

    val projects = visibleProjects(API.getPinnedRepos())
    val blogPosts = loadBlogPosts()
    val blogRoutes: Array<Route> =
        blogPosts
            .map { (meta, slug, html) -> "/blogs/${slug}" bind { blog(meta.title, html) } }
            .toTypedArray()

    routing(
        "/" bind { index() },
        "/projects" bind { projects(projects) },
        "/blogs" bind { blogs(blogPosts) },
        "/CV" bind { CV() },
        *blogRoutes,
        HttpStatusCode.NotFound bind { NotFound() },
    )

    logger.info("Done!")
}
