package me.apollointhehouse

import io.ktor.http.*
import me.apollointhehouse.data.API
import me.apollointhehouse.data.blogs.loadBlogPosts
import me.apollointhehouse.data.routing.types.PageRoute.Companion.bind
import me.apollointhehouse.data.routing.routing
import me.apollointhehouse.data.routing.types.StaticRoute.Companion.static
import me.apollointhehouse.data.routing.types.StatusRoute.Companion.status
import me.apollointhehouse.ui.components.blog
import me.apollointhehouse.ui.pages.*

private val app = routing {
    "/" bind {
        index()
    }

    val projects = visibleProjects(API.getPinnedRepos())

    "/projects" bind {
        projects(projects)
    }

    "/CV" bind {
        cv()
    }

    val blogPosts = loadBlogPosts()

    "/blogs" bind {
        blogs(blogPosts)
    }

    for ((meta, slug, html) in blogPosts) {
        slug bind {
            blog(meta.title, html)
        }
    }

    status(HttpStatusCode.NotFound) {
        notFound()
    }

    static()
}

fun main() {
    app.create()
}