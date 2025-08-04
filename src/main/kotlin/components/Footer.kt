package me.apollointhehouse.components

import kotlinx.html.*
import me.apollointhehouse.utils.themeImg

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
                        themeImg(
                            themes = SocialTheme.entries,
                            alt = social.name
                        ) { theme ->
                            media = "(prefers-color-scheme: ${theme.mode})"
                            srcset = "/assets/images/${theme.mode}-${social.icon}"
                        }
                    }
                }
            }
        }
    }
}

private enum class SocialTheme(
    val mode: String,
) {
    Dark("dark"),
    Light("light")
}

private data class Social(
    val name: String,
    val url: String,
    val icon: String
)
