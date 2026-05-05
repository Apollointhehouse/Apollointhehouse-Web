package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.blogs.BlogPost
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.components.content
import me.apollointhehouse.ui.components.utils.hxBoost

fun HTML.blogs(posts: List<BlogPost>) = base("Blogs") {
    content {
        section("blogs-list") {
            hxBoost = true

            for (post in posts.sortedByDescending { it.meta.date }) {
                val name = post.meta.title

                article {
                    a(href = post.slug) { h2 { +name } }
                    nav {
                        ul {
                            for (tag in post.meta.tags) {
                                li {
                                    p { small { kbd { +tag } } }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
