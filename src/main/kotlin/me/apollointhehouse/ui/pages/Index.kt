package me.apollointhehouse.ui.pages

import kotlinx.html.*
import kotlinx.html.article
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.components.utils.article
import me.apollointhehouse.ui.components.utils.section

fun HTML.index() = base("Home") {
    div("hero") {
        div("container") {
            hGroup {
                p { +"Hello!" }
                h1 { +"I'm Apollo!" }
                h2 { +"Student Dev from New Zealand" }
            }
        }
    }


    article(id = "about-me") {
        h3 { +"About Me" }
        p { +"I am a student from New Zealand, attending the University of Auckland." }
        p {
            +
            """
            I have experience building backend, fullstack, and general software applications using Kotlin,
            Java, and TypeScript. I've worked on desktop apps, websites, and game modifications, with experience in event
            systems, networking, and computer graphics through personal projects and hackathons.
            """
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
                """
            }

            p {
                +"I have collaborated on several shared projects with individuals of varying experience levels."
            }
            p { +"In 2025, I participated in the Terrible Ideas Hackathon at the University of Auckland." }
            p { +"In 2026, I participated in the Web3 Hackathon run by Web3 UoA." }
        }

        article(id = "education") {
            h3 { +"Education" }
            p { +"First-year student attending University of Auckland." }
            p { +"Taking a BSc in Computer Science." }

            p { +"In my last year of high school I was one of the few students to receive NCEA scholarship Digital Technologies." }
        }
    }

    section(id = "projects") {
        h3 { +"Projects" }

        for (project in topProjects) {
            article {
                header {
                    a(href = project.url) { h2 { +project.name } }
                }
                p { +project.description }
            }
        }
    }
}

private data class TopProject(
    val name: String,
    val description: String,
    val url: String,
)

private val topProjects = listOf(
    TopProject(
        name = "DataLens",
        description = "A desktop file search tool I built, this software uses indexed file system scanning, enabling fast, low-latency queries across large directories",
        url = "https://github.com/apollointhehouse/DataLens",
    ),
    TopProject(
        name = "Apollointhehouse-Web",
        description = """
            My personal website, built using the kotlin programming language, the code generates a
            static website which is then deployed to Cloudflare Pages. The code is automatically run using a GitHub Workflow
            for CI/CD. Every time the website is regenerated it automatically fetches all of my GitHub projects using the GitHub
            GraphQL API and displays them on the website
        """,
        url = "https://github.com/Apollointhehouse/Apollointhehouse-Web",
    ),
    TopProject(
        name = "Bonus3",
        description = """
            A Web3 employee bonus distribution platform my team built at the 2026 Web3 Hackathon run by the
            Web3 UoA club.
        """,
        url = "https://github.com/jameblai/web3-hackathon",
    )
)