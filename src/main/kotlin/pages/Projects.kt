package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.base
import me.apollointhehouse.components.content
import me.apollointhehouse.getPinnedRepos
import models.v2.Repo

private data class Project(
    val name: String,
    val description: String,
    val url: String,
    val topics: List<String>,
    val stars: Int
)

private fun createProject(info: Repo) = Project(
    name = info.name,
    description = info.description ?: "",
    url = info.url,
    topics = info.repositoryTopics.nodes.map { (topic, _) -> topic.name },
    stars = info.stargazerCount
)

private val projects = getPinnedRepos()
    ?.map { repo -> createProject(repo) }
    ?.toSet()
    ?.filter { "show-project" in it.topics }
    ?.sortedByDescending { it.stars }
    ?: error("Failed to get pinned repos")

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