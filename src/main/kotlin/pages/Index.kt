package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

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

        article("skills") {
            h3 { +"Skills" }

            h4 { +"Backend:" }
            ul {
                li { +"Languages: Kotlin, Java, Python, SQL" }
                li { +"Frameworks: KTor, Exposed ORM, Flask" }
            }

            h4 { +"Frontend:" }
            ul {
                li { +"Languages: HTML, CSS, JavaScript, TypeScript" }
                li { +"Frameworks: Pico Css" }
            }
        }

        article("experience") {
            h3 { +"Experience" }
            p { +"Currently, I have no professional experience, but I have participated in the Terrible Ideas Hackathon, where I worked effectively in a team. Additionally, I have collaborated on several shared GitHub projects with individuals of varying experience levels." }
        }

        article("education") {
            h3 { +"Education" }
            p { +"Senior High School Student, planning to attend the University of Auckland." }
        }

        article("metrics") {
            h3 { +"Metrics" }

            val themes = listOf(
                StatsTheme("24273a", "cad3f5", "c6a0f6", "8bd5ca", "dark-mode"),
                StatsTheme("eff1f5", "4c4f69", "8839ef", "179299", "light-mode")
            )

            for (theme in themes) {
                img(
                    loading = ImgLoading.lazy,
                    classes = "hide ${theme.mode}",
                    src = "https://github-readme-stats-one-orcin.vercel.app/api?username=apollointhehouse&show_icons=true&bg_color=${theme.bgColor}&text_color=${theme.textColor}&icon_color=${theme.iconColor}&title_color=${theme.titleColor}&hide_border=false&locale=en",
                    alt = "Apollo's Github Stats"
                )

                img(
                    loading = ImgLoading.lazy,
                    classes = "hide ${theme.mode}",
                    src = "https://github-readme-stats-one-orcin.vercel.app/api/top-langs?username=apollointhehouse&show_icons=true&bg_color=${theme.bgColor}&text_color=${theme.textColor}&icon_color=${theme.iconColor}&title_color=${theme.titleColor}&hide_border=false&layout=compact&locale=en",
                    alt = "Apollo's Most Used Languages"
                )
            }
        }
    }
    footer()
}

private data class StatsTheme(
    val bgColor: String,
    val textColor: String,
    val iconColor: String,
    val titleColor: String,
    val mode: String
)