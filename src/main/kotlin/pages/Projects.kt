package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*
import me.apollointhehouse.getPinnedRepos

private data class Project(
    val name: String,
    val description: String,
    val url: String,
)

private val projects = getPinnedRepos()
    ?.map { (info) -> Project(info.name, info.description ?: "", info.url) }
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