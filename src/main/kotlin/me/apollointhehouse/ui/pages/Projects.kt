package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.models.github.Repo
import me.apollointhehouse.ui.components.base

data class Project(
    val name: String,
    val description: String,
    val url: String,
    val topics: List<String>,
    val stars: Int,
)

fun createProject(info: Repo) =
    Project(
        name = info.name,
        description = info.description ?: "",
        url = info.url,
        topics = info.repositoryTopics.nodes.map { (topic) -> topic.name },
        stars = info.stargazerCount,
    )

fun visibleProjects(repos: List<Repo>): List<Project> =
    repos
        .map { createProject(it) }
        .toSet()
        .filter { "show-project" in it.topics }
        .sortedByDescending { it.stars }

fun HTML.projects(projects: List<Project>) = base("Projects") {
    section("projects-list") {
        val chunked = projects.chunked(2)

        for (pair in chunked) {
            div(classes = "grid") {
                for (project in pair) {
                    article {
                        header {
                            a(href = project.url) { h2 { +project.name } }
                        }
                        p { +project.description }
                    }
                }
            }
        }
    }
}
