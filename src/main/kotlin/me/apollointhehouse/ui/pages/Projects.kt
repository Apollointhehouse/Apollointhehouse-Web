package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.components.content
import me.apollointhehouse.data.API
import models.v2.Repo

private data class Project(
    val name: String,
    val description: String,
    val url: String,
    val topics: List<String>,
    val stars: Int,
)

private fun createProject(info: Repo) =
    Project(
        name = info.name,
        description = info.description ?: "",
        url = info.url,
        topics = info.repositoryTopics.nodes.map { (topic, _) -> topic.name },
        stars = info.stargazerCount,
    )

private val projects =
    API.getPinnedRepos()
        .map { repo -> createProject(repo) }
        .toSet()
        .filter { "show-project" in it.topics }
        .sortedByDescending { it.stars }

fun HTML.projects() = base("Projects") {
    content {
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
}
