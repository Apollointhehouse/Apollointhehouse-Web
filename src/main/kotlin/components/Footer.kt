package me.apollointhehouse.components

import kotlinx.html.*

private val socials = listOf(
    Social(
        name = "Github",
        url = "https://github.com/apollointhehouse",
        icon = "images/github.png",
    )
)

fun FlowContent.footer() {
    footer("container") {
        div("social") {
            p { +"Contact me:" }

            for (social in socials) {
                a {
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

private data class Social(
    val name: String,
    val url: String,
    val icon: String
)