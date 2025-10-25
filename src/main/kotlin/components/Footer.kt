package me.apollointhehouse.components

import kotlinx.html.*
import me.apollointhehouse.utils.Theme
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
                            name = social.name
                        ) { theme ->
                            "/assets/images/${theme.mode}-${social.icon}"
                        }
                    }
                }
            }
        }
    }
}

private enum class SocialTheme(
    override val mode: String,
) : Theme {
    Dark("dark"),
    Light("light")
}

private data class Social(
    val name: String,
    val url: String,
    val icon: String
)
