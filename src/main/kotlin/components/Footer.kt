package me.apollointhehouse.components

import kotlinx.html.*

private val socials = listOf(
    Social(
        name = "Github",
        url = "https://github.com/apollointhehouse",
        icon = "github.png",
    ),
    Social(
        name = "LinkedIn",
        url = "https://linkedin.com/in/apollo-cameron-boot-8061a12b8",
        icon = "linkedin.png",
    ),
//    Social(
//        name = "Bluesky",
//        url = "https://bsky.app/profile/apollointhehouse.bsky.social",
//        icon = "bluesky.png",
//    ),

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
                    a(href = social.url, classes = "contrast", target = "_blank") {
                        img(classes = "hide dark-mode", src = "/images/light-${social.icon}", alt = social.name)
                        img(classes = "hide light-mode", src = "/images/dark-${social.icon}", alt = social.name)
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
