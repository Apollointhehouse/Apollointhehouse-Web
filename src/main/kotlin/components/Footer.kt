package me.apollointhehouse.components

import kotlinx.html.*

private val socials = listOf(
    Social(
        name = "Github",
        url = "https://github.com/apollointhehouse",
        icon = "/images/github.png",
    ),
    Social(
        name = "LinkedIn",
        url = "https://linkedin.com/in/apollo-cameron-boot-8061a12b8",
        icon = "/images/linkedin.png",
    ),
    Social(
        name = "Bluesky",
        url = "https://bsky.app/profile/apollointhehouse.bsky.social",
        icon = "/images/bluesky.png",
    ),

)

fun FlowContent.footer() = footer {
    nav("container") {
        ul {
            li {
                a(href = "https://github.com/Apollointhehouse/Apollointhehouse-Web") { small { +"Source" } }
            }
        }

        ul {
            for (social in socials) {
                li("social") {
                    a("contrast") {
                        href = social.url
                        target = "_blank"
                        img(classes = "dark-mode") {
                            src = social.icon
                            alt = social.name
                        }
                    }
                }
            }
        }
    }
}

private data class Social(
    val name: String,
    val url: String,
    val icon: String
)