package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.blogs.BlogPost
import me.apollointhehouse.ui.html.extFxPush
import me.apollointhehouse.ui.html.fxAction
import me.apollointhehouse.ui.html.fxSwap
import me.apollointhehouse.ui.html.fxTarget
import me.apollointhehouse.ui.html.title

fun FlowContent.blogs(posts: List<BlogPost>) {
    title { +"Blogs" }

    section("blogs-list") {
        for (post in posts.sortedByDescending { it.meta.date }) {
            val name = post.meta.title

            article {
                a {
                    fxAction = post.fragment
                    fxTarget = "main"
                    fxSwap = "innerHTML"
                    extFxPush = true

                    href = post.slug
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
