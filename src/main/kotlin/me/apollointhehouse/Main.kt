package me.apollointhehouse

import io.ktor.http.*
import me.apollointhehouse.data.API
import me.apollointhehouse.data.blogs.loadBlogPosts
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.StatusRoute.Companion.bind
import me.apollointhehouse.data.routing.PageRoute.Companion.bind
import me.apollointhehouse.data.routing.Router
import me.apollointhehouse.data.routing.routing
import me.apollointhehouse.data.routing.setupResources
import me.apollointhehouse.ui.components.blog
import me.apollointhehouse.ui.pages.*

private val logger = logger {}

private val app = routing {
    "/" bind {
        index()
    }
    "/projects" bind {
        projects(visibleProjects(API.getPinnedRepos()))
    }
    "/CV" bind {
        CV()
    }

    HttpStatusCode.NotFound bind {
        NotFound()
    }

    blogRoutes()
}

fun main() {
    logger.info("Generating Static Pages...")

    setupResources()
    app.create()

    logger.info("Done!")
}

context(_: Router.Builder)
private fun blogRoutes() {
    val blogPosts = loadBlogPosts()

    "/blogs" bind {
        blogs(blogPosts)
    }

    for ((meta, slug, html) in blogPosts) {
        "/blogs/${slug}" bind {
            blog(meta.title, html)
        }
    }
}
