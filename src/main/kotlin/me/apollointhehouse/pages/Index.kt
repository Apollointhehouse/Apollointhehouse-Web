package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.Config.README_STATS_API
import me.apollointhehouse.components.base
import me.apollointhehouse.components.content
import me.apollointhehouse.utils.Resources.imgDownload
import me.apollointhehouse.utils.Theme
import me.apollointhehouse.utils.article
import me.apollointhehouse.utils.themeImg

fun HTML.index() =
    base("Home") {
        div("hero") {
            div("container") {
                hGroup {
                    p { +"Hello!" }
                    h1 { +"I'm Apollo!" }
                    h2 { +"Student Dev from New Zealand" }
                }
            }
        }

        content {
            article(id = "about-me") {
                h3 { +"About Me" }
                p { +"I am a student from New Zealand, attending the University of Auckland." }
                p {
                    +
                        """
                        I have experience in game modding using java/kotlin, 
                        I am very interested in getting into embedded development and robotics.
                        """.trimIndent()
                        .split("\n")
                        .joinToString("\n")
                }
            }

            div(classes = "grid") {
                id = "home-grid"
                article(id = "skills") {
                    h3 { +"Skills" }

                    h4 { +"Backend and Game Modding:" }
                    ul {
                        li { +"Languages: Kotlin, Java, Python, SQL" }
                        li { +"Frameworks: KTor, Exposed DAO, Flask" }
                    }

                    h4 { +"Frontend:" }
                    ul {
                        li { +"Languages: Kotlin, HTML, CSS, JavaScript" }
                        li { +"Frameworks: Compose Multiplatform, Pico CSS" }
                    }
                }

                article(id = "experience") {
                    h3 { +"Experience" }
                    p {
                        +
                            """
                            I have worked on server java/kotlin based mods for a forked version of Minecraft, 
                            I also work on a *cheat client* for this game, 
                            which has given me an understanding of exploits in network related code.
                            """.trimIndent()
                            .split("\n")
                            .joinToString(" ")
                    }

                    p {
                        +"I have collaborated on several shared projects with individuals of varying experience levels."
                    }
                    p { +"In 2025, I participated in the Terrible Ideas Hackathon at the University of Auckland." }
                }

                article(id = "education") {
                    h3 { +"Education" }
                    p { +"First-year student attending University of Auckland." }
                    p { +"Doing a Bachelor of Science, majoring in Computer Science." }
                }
            }

            article(id = "metrics") {
                h3 { +"Metrics" }

                themeImg(
                    themes = StatsTheme.entries,
                    name = "Apollo's Github Stats",
                ) { statsImg(it) }

                themeImg(
                    themes = StatsTheme.entries,
                    name = "Apollo's Most Used Languages",
                ) { langsImg(it) }
            }
        }
    }

private fun statsImg(theme: StatsTheme) =
    imgDownload(
        "stats-${theme.mode}",
        """
        $README_STATS_API?
        username=apollointhehouse&
        show_icons=true&
        bg_color=${theme.bgColor}&
        text_color=${theme.textColor}&
        icon_color=${theme.iconColor}&
        title_color=${theme.titleColor}&
        hide_border=false&
        locale=en
        """.trimIndent()
            .split("\n")
            .joinToString(""),
        "svg",
    )

private fun langsImg(theme: StatsTheme) =
    imgDownload(
        "langs-${theme.mode}",
        """
        $README_STATS_API/top-langs?
        username=apollointhehouse&
        show_icons=true&
        bg_color=${theme.bgColor}&
        text_color=${theme.textColor}&
        icon_color=${theme.iconColor}&
        title_color=${theme.titleColor}&
        hide_border=false&
        layout=compact&
        locale=en
        """.trimIndent()
            .split("\n")
            .joinToString(""),
        "svg",
    )

private enum class StatsTheme(
    val bgColor: String,
    val textColor: String,
    val iconColor: String,
    val titleColor: String,
    override val mode: String,
) : Theme {
    Dark("24273a", "cad3f5", "c6a0f6", "8bd5ca", "dark"),
    Light("eff1f5", "4c4f69", "8839ef", "179299", "light"),
}
