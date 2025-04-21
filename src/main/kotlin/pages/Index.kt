package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

fun HTML.index() = base("Home") {
    div("hero") {
        navbar(Page("Home", ""), Page("Projects", "projects"), Page("Blogs", "blogs"))

        div("container") {
            hGroup {
                p { +"Hello!" }
                h1 { +"I'm Apollo!" }
                h2 { +"Kotlin Enthusiast from New Zealand" }
            }
        }
    }
    main("container") {
        section("about-me") {
            h3 { +"About Me" }
            p { +"I am a high school student from New Zealand, planning to attend the University of Auckland." }
            p { +"I am particularly interested in backend development, especially using Kotlin." }
        }

        section("skills") {
            h3 { +"Skills" }
            ul {
                li { +"Proficient in Kotlin, Java, Python, HTML, CSS, and JavaScript." }
                li { +"Backend: SQL, Ktor, Flask" }
            }

            img {
                src = "https://github-readme-stats-one-orcin.vercel.app/api/top-langs?username=apollointhehouse&show_icons=true&bg_color=24273a&text_color=cad3f5&icon_color=c6a0f6&title_color=8bd5ca&hide_border=false&layout=compact&locale=en"
                alt = "Apollo's Most Used Languages"
            }
        }

        section("experience") {
            h3 { +"Experience" }
            p { +"Currently, I have no professional experience, but I have participated in the Terrible Ideas Hackathon, where I worked effectively in a team. Additionally, I have collaborated on several shared GitHub projects with individuals of varying experience levels." }
        }

        section("education") {
            h3 { +"Education" }
            p { +"Senior High School Student, planning to attend the University of Auckland." }
        }

        section("stats") {
            h3 { +"Stats" }

            img {
                src = "https://github-readme-stats-one-orcin.vercel.app/api?username=apollointhehouse&show_icons=true&bg_color=24273a&text_color=cad3f5&icon_color=c6a0f6&title_color=8bd5ca&hide_border=false&locale=en"
                alt = "Apollo's Github Stats"
            }
        }
    }
    footer()
}
