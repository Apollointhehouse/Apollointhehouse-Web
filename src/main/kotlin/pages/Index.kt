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
            h3 { +"Skills" }
            ul {
                li { +"Proficient in Kotlin, Java, Python, HTML, CSS, and JavaScript." }
                li { +"Backend: SQL, Ktor, Flask" }
            }
            h3 { +"Experience" }
            p { +"Currently, I have no professional experience, but I have participated in the Terrible Ideas Hackathon, where I worked effectively in a team. Additionally, I have collaborated on several shared GitHub projects with individuals of varying experience levels." }
            h3 { +"Education" }
            p { +"Senior High School Student, planning to attend the University of Auckland." }
        }
    }
    footer()
}