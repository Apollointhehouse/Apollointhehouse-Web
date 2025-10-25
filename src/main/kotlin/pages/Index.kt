package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.Config.readmeStatsAPI
import me.apollointhehouse.components.*
import me.apollointhehouse.utils.Resources.imgDownload
import me.apollointhehouse.utils.Theme
import me.apollointhehouse.utils.article
import me.apollointhehouse.utils.themeImg

fun HTML.index() = base("Home") {
    div("hero") {
        div("container") {
            hGroup {
                p { +"Hello!" }
                h1 { +"I'm Apollo!" }
                h2 { +"Hobbyist Dev from New Zealand" }
            }
        }
    }

    content {
        article(id = "about-me") {
            h3 { +"About Me" }
            p { +"I am a high school student from New Zealand, planning to attend the University of Auckland." }
            p { +"I am particularly interested in backend development, especially using Kotlin." }
        }

        div(classes = "grid") {
            id = "home-grid"
            article(id = "skills") {
                h3 { +"Skills" }

                h4 { +"Backend:" }
                ul {
                    li { +"Languages: Kotlin, Java, Python, SQL" }
                    li { +"Frameworks: KTor, Exposed DAO, Flask" }
                }

                h4 { +"Frontend:" }
                ul {
                    li { +"Languages: Kotlin, HTML, CSS, JavaScript" }
                    li { +"Frameworks: Compose Multiplatform, Pico Css" }
                }
            }

            article(id = "experience") {
                h3 { +"Experience" }
                p { +"I have collaborated on several shared projects with individuals of varying experience levels. Additionally I have also participated in the Terrible Ideas Hackathon." }
            }

            article(id = "education") {
                h3 { +"Education" }
                p { +"Senior High School Student, planning to attend the University of Auckland." }
            }
        }

        article(id = "metrics") {
            h3 { +"Metrics" }

            themeImg(
                themes = StatsTheme.entries,
                name = "Apollo's Github Stats"
            ) { theme ->
                imgDownload(
                    "stats-${theme.mode}",
                    "$readmeStatsAPI?username=apollointhehouse&show_icons=true&bg_color=${theme.bgColor}&text_color=${theme.textColor}&icon_color=${theme.iconColor}&title_color=${theme.titleColor}&hide_border=false&locale=en",
                    "svg"
                )
            }

            themeImg(
                themes = StatsTheme.entries,
                name = "Apollo's Most Used Languages"
            ) { theme ->
                imgDownload(
                    "langs-${theme.mode}",
                    "$readmeStatsAPI/top-langs?username=apollointhehouse&show_icons=true&bg_color=${theme.bgColor}&text_color=${theme.textColor}&icon_color=${theme.iconColor}&title_color=${theme.titleColor}&hide_border=false&layout=compact&locale=en",
                    "svg"
                )
            }
        }
    }
}

private enum class StatsTheme(
    val bgColor: String,
    val textColor: String,
    val iconColor: String,
    val titleColor: String,
    override val mode: String
) : Theme {
    Dark("24273a", "cad3f5", "c6a0f6", "8bd5ca", "dark"),
    Light("eff1f5", "4c4f69", "8839ef", "179299", "light")
}