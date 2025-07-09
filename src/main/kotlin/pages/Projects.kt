package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.base
import me.apollointhehouse.components.footer
import me.apollointhehouse.components.hero
import me.apollointhehouse.getPinnedRepos
import me.apollointhehouse.models.RepoInfo
import me.apollointhehouse.utils.details
import me.apollointhehouse.utils.loadComponent
import me.apollointhehouse.utils.toComponent

private data class Project(
    val name: String,
    val description: String,
    val url: String,
    val readmeComponent: String?,
)

private fun createProject(info: RepoInfo): Project =
    Project(
        name = info.name,
        description = info.description ?: "",
        url = info.url,
        readmeComponent = info.readme?.toComponent("readme-${info.name}"),
    )

private val projects = getPinnedRepos()
    ?.map { (info) -> createProject(info) }
    ?: error("Failed to get pinned repos")

fun HTML.projects() = base("Projects") {
    hero()

    main(classes = "container") {
        section("projects-list") {
            for (project in projects) {
                article {
                    a(href = project.url) { h2 { +project.name } }
                    p { +project.description }

                    if (project.readmeComponent != null) {
                        details(name = "readme") {
                            summary {
                                classes = setOf("outline", "secondary")
                                strong { +"More Info" }
                            }

                            hr()

                            loadComponent(project.readmeComponent) {
                                classes = setOf("overflow-auto")
                                style = "max-height: 50vh"
                            }
                        }
                    }
                }
            }
        }
    }
    footer()
}