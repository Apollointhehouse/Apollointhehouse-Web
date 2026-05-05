package me.apollointhehouse

import io.ktor.http.HttpStatusCode
import kotlinx.html.HTML
import me.apollointhehouse.data.API
import me.apollointhehouse.data.blogs.loadBlogPosts
import me.apollointhehouse.data.errorRouting
import me.apollointhehouse.data.routing
import me.apollointhehouse.data.setupResources
import me.apollointhehouse.ui.components.blog
import me.apollointhehouse.ui.pages.CV
import me.apollointhehouse.ui.pages.NotFound
import me.apollointhehouse.ui.pages.blogs
import me.apollointhehouse.ui.pages.index
import me.apollointhehouse.ui.pages.projects
import me.apollointhehouse.ui.pages.visibleProjects

fun main() {
    setupResources()

    val projects = visibleProjects(API.getPinnedRepos())
    val blogPosts = loadBlogPosts()
    val blogRoutes: Array<Pair<String, HTML.() -> Unit>> =
        blogPosts
            .map { post ->
                val page: HTML.() -> Unit = { blog(post.meta.title, post.html) }
                "/blogs/${post.slug}" to page
            }
            .toTypedArray()

    routing(
        "/" to { index() },
        "/projects" to { projects(projects) },
        "/blogs" to { blogs(blogPosts) },
        "/CV" to { CV() },
        *blogRoutes,
    )

    errorRouting(
        HttpStatusCode.NotFound to { NotFound() },
    )

    println("Done!")
}
