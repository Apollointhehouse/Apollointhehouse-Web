package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*
import me.apollointhehouse.getPinnedRepos

data class Project(
    val name: String,
    val description: String,
    val url: String,
)

val projects = getPinnedRepos()
    ?.map { Project(it.name, it.description ?: "", it.url) }
    ?: error("Failed to get pinned repos")

fun HTML.projects() = base("Projects") {
    div("hero") {
        navbar(Page("Home", "../"), Page("Projects", ""), Page("Blogs", "../blogs"))
    }
    main(classes = "container") {
        section("projects-list") {
            for (project in projects) {
                article {
                    a(href = project.url) { h2 { +project.name } }
                    p { +project.description }
                }
            }
        }
    }
    footer()
}