package me.apollointhehouse.ui.components

import kotlinx.html.*
import me.apollointhehouse.ui.html.unsafe

private val socials =
    listOf(
        Social(
            name = "Github",
            url = "https://github.com/apollointhehouse",
            icon = "github",
        ),
        Social(
            name = "LinkedIn",
            url = "https://linkedin.com/in/apollo-cameron-boot-8061a12b8",
            icon = "linkedin",
        ),
    )

fun FlowContent.footer() =
    div("footer") {
        nav("container-fluid") {
            ul {
                li {
                    a(href = "https://github.com/Apollointhehouse/Apollointhehouse-Web") { small { +"Source" } }
                }
            }

            ul {
                for (social in socials) {
                    li("social") {
                        a(href = social.url, classes = "contrast", target = "_blank") {
                            attributes["aria-label"] = social.name
                            unsafe { raw(inlineSvg(social.icon)) }
                        }
                    }
                }
            }
        }
    }

private data class Social(
    val name: String,
    val url: String,
    val icon: String,
)
