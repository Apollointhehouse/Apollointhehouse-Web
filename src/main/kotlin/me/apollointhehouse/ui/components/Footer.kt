package me.apollointhehouse.ui.components

import kotlinx.html.*
import me.apollointhehouse.ui.html.Theme

private val socials =
    listOf(
        Social(
            name = "Github",
            url = "https://github.com/apollointhehouse",
            icon = "github.svg",
        ),
        Social(
            name = "LinkedIn",
            url = "https://linkedin.com/in/apollo-cameron-boot-8061a12b8",
            icon = "linkedin.svg",
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
                        style {
                            unsafe {

                            }
                        }

                        a(href = social.url, classes = "contrast", target = "_blank") {
                            themedImg(
                                themes = SocialTheme.entries,
                                name = social.name,
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
    Light("light"),
}

private data class Social(
    val name: String,
    val url: String,
    val icon: String,
)
