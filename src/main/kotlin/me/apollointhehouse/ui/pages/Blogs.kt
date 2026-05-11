package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.blogs.BlogPost
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.components.utils.hxGet
import me.apollointhehouse.ui.components.utils.hxPushUrl
import me.apollointhehouse.ui.components.utils.hxSwap
import me.apollointhehouse.ui.components.utils.hxTarget

fun HTML.blogs(posts: List<BlogPost>) = base("Blogs") {
    section("blogs-list") {
        for (post in posts.sortedByDescending { it.meta.date }) {
            val name = post.meta.title

            article {
                a {
                    hxGet = post.partial
                    hxTarget = "main"
                    hxSwap = "innerHTML show:none"
                    hxPushUrl = post.slug

                    href = post.slug
                    attributes["preload"] = ""
                    h2 { +name }
                }
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
