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


fun main() = routing {
    fragment("/", "Home") {
        index()
    }

    val projects = visibleProjects(API.getPinnedRepos())

    fragment("/projects", "Projects") {
        projects(projects)
    }

    val blogPosts = loadBlogPosts()

    fragment("/blogs", "Blogs") {
        blogs(blogPosts)
    }

    for ((meta, slug, html) in blogPosts) {
        fragment(slug, meta.title) {
            title { +meta.title }
            blog(html)
        }
    }

    val cvs = fetchCVs()

    for ([branch, resource] in cvs) {
        if (branch == "master") {
            page("/cv") {
                cv("CV", resource)
            }

            continue
        }

        page("/cv/$branch") {
            cv(branch, resource)
        }
    }

    status(HttpStatusCode.NotFound) {
        notFound()
    }

    static(Path("./src/main/resources/static"))
}