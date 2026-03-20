package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.Config.README_STATS_API
import me.apollointhehouse.data.Resources.download
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.components.content
import me.apollointhehouse.ui.components.utils.Theme
import me.apollointhehouse.ui.components.utils.article
import me.apollointhehouse.ui.components.themedImg

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
                        I have experience in game modding using java/kotlin and in working on collaborative projects with others, 
                        I am very interested in the application to software to real world issues
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
                        li { +"Languages: Java, Python, Kotlin, SQL" }
                        li { +"Frameworks: KTor, Exposed DAO, Flask, Fabric, Sponge Mixin" }
                    }

                    h4 { +"Frontend:" }
                    ul {
                        li { +"Languages: HTML, CSS, JavaScript" }
                        li { +"Frameworks: Compose Multiplatform, Pico CSS" }
                    }
                }

                article(id = "experience") {
                    h3 { +"Experience" }
                    p {
                        +
                            """
                            I have worked on several java/kotlin based mods for a forked version of Minecraft, 
                            which has given me an understanding of how to work with legacy code bases
                            and how to solve/workaround issues in software development.
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
                    p { +"Taking a BsC in Computer Science." }

                    p { +"In my last year of high school I was one of the few students to receive NCEA scholarship Digital Technologies." }
                }
            }

            article(id = "metrics") {
                h3 { +"Metrics" }

                themedImg(
                    themes = StatsTheme.entries,
                    name = "Apollo's Github Stats",
                ) { it.statsGraph() }

                themedImg(
                    themes = StatsTheme.entries,
                    name = "Apollo's Most Used Languages",
                ) { it.languageGraph() }
            }
        }
    }




private enum class StatsTheme(
    bgColor: String,
    textColor: String,
    iconColor: String,
    titleColor: String,
    override val mode: String,
) : Theme {
    Dark("24273a", "cad3f5", "c6a0f6", "8bd5ca", "dark"),
    Light("eff1f5", "4c4f69", "8839ef", "179299", "light");

    private val languageQuery = "${README_STATS_API}/top-langs?username=apollointhehouse&show_icons=true&bg_color=${bgColor}&text_color=${textColor}&icon_color=${iconColor}&title_color=${titleColor}&hide_border=false&layout=compact&locale=en"
    private val statsQuery = "${README_STATS_API}?username=apollointhehouse&show_icons=true&bg_color=${bgColor}&text_color=${textColor}&icon_color=${iconColor}&title_color=${titleColor}&hide_border=false&locale=en"

    fun languageGraph() = download(
        name = "langs-$mode",
        url = languageQuery,
        path = "/assets/images",
        ext = "svg"
    )

    fun statsGraph() = download(
        name = "stats-$mode",
        url = statsQuery,
        path = "/assets/images",
        ext = "svg",
    )
}
