package me.apollointhehouse

import io.ktor.http.*
import me.apollointhehouse.data.API
import me.apollointhehouse.data.blogs.loadBlogPosts
import me.apollointhehouse.data.routing.routing
import me.apollointhehouse.data.routing.types.FragmentRoute.Companion.fragment
import me.apollointhehouse.data.routing.types.PageRoute.Companion.page
import me.apollointhehouse.data.routing.types.StaticRoute.Companion.static
import me.apollointhehouse.data.routing.types.StatusRoute.Companion.status
import me.apollointhehouse.ui.components.blog
import me.apollointhehouse.ui.html.title
import me.apollointhehouse.ui.pages.*
import kotlin.io.path.Path

private val app = routing {
    fragment("/") {
        index()
    }

    val projects = visibleProjects(API.getPinnedRepos())

    fragment("/projects") {
        projects(projects)
    }


    val blogPosts = loadBlogPosts()

    fragment("/blogs") {
        blogs(blogPosts)
    }

    for ((meta, slug, html) in blogPosts) {
        fragment(slug) {
            title { +meta.title }
            blog(html)
        }
    }

    page("/CV") {
        cv()
    }

    status(HttpStatusCode.NotFound) {
        notFound()
    }

    static(Path("./src/main/resources/static"))
}

fun main() {
    app.create()
}