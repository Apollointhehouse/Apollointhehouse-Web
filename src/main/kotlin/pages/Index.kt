package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.Config
import me.apollointhehouse.components.*
import me.apollointhehouse.utils.themeImg
import java.io.File
import java.net.URI

fun HTML.index() = base("Home") {
    hero {
        div("container") {
            hGroup {
                p { +"Hello!" }
                h1 { +"I'm Apollo!" }
                h2 { +"Hobbyist Dev from New Zealand" }
            }
        }
    }

    content {
        article("about-me") {
            h3 { +"About Me" }
            p { +"I am a high school student from New Zealand, planning to attend the University of Auckland." }
            p { +"I am particularly interested in backend development, especially using Kotlin." }
        }

        div(classes = "grid") {
            id = "home-grid"
            article("skills") {
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

            article("experience") {
                h3 { +"Experience" }
                p { +"I have collaborated on several shared projects with individuals of varying experience levels. Additionally I have also participated in the Terrible Ideas Hackathon." }
            }

            article("education") {
                h3 { +"Education" }
                p { +"Senior High School Student, planning to attend the University of Auckland." }
            }
        }

        article("metrics") {
            h3 { +"Metrics" }

            themeImg(
                themes = StatsTheme.entries,
                alt = "Apollo's Github Stats"
            ) { theme ->
                media = "(prefers-color-scheme: ${theme.mode})"
                srcset = svgRes("stats-${theme.mode}", "https://github-readme-stats-one-orcin.vercel.app/api?username=apollointhehouse&show_icons=true&bg_color=${theme.bgColor}&text_color=${theme.textColor}&icon_color=${theme.iconColor}&title_color=${theme.titleColor}&hide_border=false&locale=en")
            }

            themeImg(
                themes = StatsTheme.entries,
                alt = "Apollo's Most Used Languages"
            ) { theme ->
                media = "(prefers-color-scheme: ${theme.mode})"
                srcset = svgRes("langs-${theme.mode}", "https://github-readme-stats-one-orcin.vercel.app/api/top-langs?username=apollointhehouse&show_icons=true&bg_color=${theme.bgColor}&text_color=${theme.textColor}&icon_color=${theme.iconColor}&title_color=${theme.titleColor}&hide_border=false&layout=compact&locale=en")
            }
        }
    }
    footer()
}

private fun svgRes(name: String, url: String): String {
    val dir = File("${Config.base}/assets/images/$name.svg")
    dir.createNewFile()

    dir.writeText(URI(url).toURL().readText())

    return "/assets/images/$name.svg"
}

private enum class StatsTheme(
    val bgColor: String,
    val textColor: String,
    val iconColor: String,
    val titleColor: String,
    val mode: String
) {
    Dark("24273a", "cad3f5", "c6a0f6", "8bd5ca", "dark"),
    Light("eff1f5", "4c4f69", "8839ef", "179299", "light")
}