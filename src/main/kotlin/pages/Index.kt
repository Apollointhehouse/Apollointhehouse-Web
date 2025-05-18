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
        article("about-me") {
            h3 { +"About Me" }
            p { +"I am a high school student from New Zealand, planning to attend the University of Auckland." }
            p { +"I am particularly interested in backend development, especially using Kotlin." }
        }

        article("skills") {
            h3 { +"Skills" }
            ul {
                li {
                    h4 { +"Backend:" }
                    ul {
                        li { +"Languages: Kotlin, Java, Python, SQL" }
                        li { +"Frameworks: KTor, Flask, Javalin" }
                    }
                }

                li {
                    h4 { +"Frontend:" }
                    ul {
                        li { +"Languages: HTML, CSS, JavaScript" }
//                        li { +"Frameworks: Vue" }
                    }
                }
            }

//            img {
//                src = "https://github-readme-stats-one-orcin.vercel.app/api/top-langs?username=apollointhehouse&show_icons=true&bg_color=24273a&text_color=cad3f5&icon_color=c6a0f6&title_color=8bd5ca&hide_border=false&layout=compact&locale=en"
//                alt = "Apollo's Most Used Languages"
//            }
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

            img {
                src =
                    "https://github-readme-stats-one-orcin.vercel.app/api?username=apollointhehouse&show_icons=true&bg_color=24273a&text_color=cad3f5&icon_color=c6a0f6&title_color=8bd5ca&hide_border=false&locale=en"
                alt = "Apollo's Github Stats"
            }

            img {
                src =
                    "https://github-readme-stats-one-orcin.vercel.app/api/top-langs?username=apollointhehouse&show_icons=true&bg_color=24273a&text_color=cad3f5&icon_color=c6a0f6&title_color=8bd5ca&hide_border=false&layout=compact&locale=en"
                alt = "Apollo's Most Used Languages"
            }
        }
    }
    footer()
}
