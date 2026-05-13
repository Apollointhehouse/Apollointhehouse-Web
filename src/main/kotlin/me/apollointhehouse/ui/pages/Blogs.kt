package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.blogs.BlogPost
import me.apollointhehouse.ui.html.hxGet
import me.apollointhehouse.ui.html.hxPushUrl
import me.apollointhehouse.ui.html.hxSwap
import me.apollointhehouse.ui.html.hxTarget
import me.apollointhehouse.ui.html.title

fun FlowContent.blogs(posts: List<BlogPost>) {
    title { +"Blogs" }

    section("blogs-list") {
        for (post in posts.sortedByDescending { it.meta.date }) {
            val name = post.meta.title

            article {
                a {
                    hxGet = post.fragment
                    hxTarget = "main"
                    hxSwap = "innerHTML show:none"
                    hxPushUrl = post.slug

                    href = post.slug
                    attributes["preload"] = "mouseover"
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
