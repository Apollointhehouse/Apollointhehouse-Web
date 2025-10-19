package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*
import me.apollointhehouse.models.Markdown
import me.apollointhehouse.utils.routing
import me.apollointhehouse.utils.BlogData
import me.apollointhehouse.utils.hxBoost
import me.apollointhehouse.utils.toHtml
import java.io.File

data class Blog(
    val meta: BlogData,
    val page: HTML.() -> Unit
)

private fun generateBlogs(): List<Blog> {
    val texts = File("./src/main/resources/blogs")
        .walkTopDown()
        .filter { it.extension == "md" }
        .map { it.readText() }

    val blogs: List<Blog> = texts
        .map { text -> parseBlogs(text) }
        .map { (meta, html) -> Blog(meta) { blog(meta.title, html) } }
        .also {
            val routes = it.associate { (meta, page) -> "/blogs/${meta.title.replace(" ", "-")}" to page }.toList().toTypedArray()
            routing(*routes)
        }
        .toList()

    return blogs
}

private operator fun Blog.compareTo(other: Blog): Int {
    val (meta, _) = this
    val (meta1, _) = other

    return meta.date.compareTo(meta1.date)
}

private fun parseBlogs(text: String): Pair<BlogData, String> {
    val split = text.lines().indexOfFirst { it == "---" }

    val meta = text
        .lines()
        .take(split)
        .associate {
            val i = it.indexOf("=")
            it.substring(0, i) to it.substring(i + 1)
        }
        .let { BlogData(it) }

    val html = Markdown(text
        .lines()
        .drop(split + 1)
        .joinToString("\n")
    ).toHtml()

    return meta to html
}

fun HTML.blogs() = base("Blogs") {
    hero()
    content {
        section("blogs-list") {
            hxBoost = true

            val blogs = generateBlogs()
                .sortedWith { blog, other -> other.compareTo(blog) }

            for ((meta, _) in blogs) {
                val name = meta.title

                article {
                    a(href = name.replace(" ", "-")) { h2 { +name } }
                    nav {
                        ul {
                            for (tag in meta.tags) li {
                                p { small { kbd { +tag } } }
                            }
                        }
                    }
                }
            }
        }
    }
    footer()
}