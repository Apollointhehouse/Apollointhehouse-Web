package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*
import me.apollointhehouse.models.Markdown
import me.apollointhehouse.setupRoutes
import me.apollointhehouse.utils.toHtml
import java.io.File

private fun generateBlogs(): Map<String, HTML.() -> Unit> {
    val texts = File("./src/main/resources/blogs")
        .listFiles()
        .map { it.name.substringBefore(".md") to it.readText() }

    val routes = texts.associate { (name, text) ->
        val html = Markdown(text).toHtml()
        val page: HTML.() -> Unit = { blog(name, html) }

        "/blogs/$name" to page
    }

    setupRoutes(routes)

    return routes
}

fun HTML.blogs() = base("Blogs") {
    hero()
    content {
        section("blogs-list") {
            val blogs = generateBlogs()

            for ((route, _) in blogs) {
                val name = route.substringAfter("/blogs/")

                article {
                    a(href = name) { h2 { +name } }
                }
            }
        }
    }
    footer()
}