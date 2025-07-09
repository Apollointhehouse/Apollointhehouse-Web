package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.Page
import me.apollointhehouse.components.base
import me.apollointhehouse.components.footer
import me.apollointhehouse.components.navbar
import me.apollointhehouse.getPinnedRepos
import me.apollointhehouse.models.RepoInfo
import me.apollointhehouse.utils.Markdown
import me.apollointhehouse.utils.component
import me.apollointhehouse.utils.details
import me.apollointhehouse.utils.iframe
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
        readmeSrc = info.readme?.let { component("readme-${info.name}", Markdown(it.text).toHtml()) }
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
                        details(name = "readme") {
                            summary {
                                classes = setOf("outline", "secondary")
                                strong { +"More Info" }
                            }

                            hr()

                            div {
                                attributes["aria-busy"] = "true"

                                iframe(loading = "lazy") {
                                    src = "../${project.readmeSrc}"
                                    width = "0"
                                    height = "0"
                                    onLoad = "this.parentNode.replaceWith(...this.contentDocument.body.childNodes);"
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