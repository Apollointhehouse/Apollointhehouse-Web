package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.Page
import me.apollointhehouse.components.base
import me.apollointhehouse.components.footer
import me.apollointhehouse.components.navbar
import me.apollointhehouse.getPinnedRepos
import me.apollointhehouse.models.RepoInfo
import me.apollointhehouse.utils.Markdown
import me.apollointhehouse.utils.createComponent
import me.apollointhehouse.utils.loadComponent
import me.apollointhehouse.utils.toHtml

private data class Project(
    val name: String,
    val description: String,
    val url: String,
    val readmeSrc: String?,
)

private fun createProject(info: RepoInfo): Project =
    Project(
        name = info.name,
        description = info.description ?: "",
        url = info.url,
        readmeSrc = info.readme?.let { createComponent("readme-${info.name}", Markdown(it.text).toHtml()) }
    )

private val projects = getPinnedRepos()
    ?.map { (info) -> createProject(info) }
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

                    if (project.readmeSrc != null) {
                        details {
                            summary {
                                classes = setOf("outline", "secondary")
                                strong { +"More Info" }
                            }

                            hr()

                            loadComponent(project.readmeSrc)
                        }
                    }
                }
            }
        }
    }
    footer()
}