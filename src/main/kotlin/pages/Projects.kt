package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.base
import me.apollointhehouse.components.content
import me.apollointhehouse.components.footer
import me.apollointhehouse.components.hero
import me.apollointhehouse.getPinnedRepos
import me.apollointhehouse.models.RepoInfo
import me.apollointhehouse.utils.details
import me.apollointhehouse.utils.toComponent

private data class Project(
    val name: String,
    val description: String,
    val url: String,
    val component: String?,
)

private fun createProject(info: RepoInfo) = Project(
    name = info.name,
    description = info.description ?: "",
    url = info.url,
    component = info.readme?.toComponent("readme-${info.name}"),
)

private val projects = getPinnedRepos()
    ?.map { (info) -> createProject(info) }
    ?: error("Failed to get pinned repos")

fun HTML.projects() = base("Projects") {
    script { src = "https://cdn.jsdelivr.net/npm/htmx.org@2.0.7/dist/htmx.min.js" }

    hero()

    content {
        section("projects-list") {
            for (project in projects) {
                article {
                    a(href = project.url) { h2 { +project.name } }
                    p { +project.description }

                    if (project.component != null) {
                        details(name = "readme") {
                            summary {
                                classes = setOf("outline", "secondary")
                                strong { +"More Info" }
                            }

                            hr()

                            div {
                                classes = setOf("overflow-auto")
                                style = "max-height: 50vh"

                                div {
                                    attributes["hx-trigger"] = "load"
                                    attributes["hx-get"] = "/components/${project.component}"
                                    attributes["hx-swap"] = "outerHTML"
                                    attributes["aria-busy"] = "true"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    footer()
}