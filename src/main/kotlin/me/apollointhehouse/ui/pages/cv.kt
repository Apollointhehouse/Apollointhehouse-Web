package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.API
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.resources.GithubArtifactResource
import me.apollointhehouse.data.resources.ResolvedResource
import me.apollointhehouse.data.resources.Resources

context(resources: Resources, _: Config)
fun HTML.cv(name: String, resource: ResolvedResource) {
    lang = "en"

    head {
        meta(charset = "utf-8")
        meta(name = "viewport", content = "width=device-width, initial-scale=1")
        meta(name = "color-scheme", content = "light dark")

        style {
            unsafe {
                raw(resources.picoCSS.resolve().toString())
            }
        }

        style {
            unsafe {
                raw(resources.styleCSS.resolve().toString())
            }
        }
        link(rel = "icon", type = "image/x-icon", href = "/assets/images/icon.ico")

        meta(
            name = "keywords",
            content = "NZ, New Zealand, Apollointhehouse, Apollo, Kotlin, Backend, Developer, UOA, University of Auckland",
        )
        meta(name = "author", content = "Apollointhehouse")
        meta(name = "canonical", content = "apollointhehouse.dev")
        title { +name }
        meta {
            attributes["property"] = "og:title"
            content = name
        }
        meta {
            attributes["property"] = "og:title"
            content = name
        }

        meta {
            attributes["property"] = "og:site_name"
            content = "Apollointhehouse"
        }

        meta {
            attributes["property"] = "og:description"
            content = "Personal website for Apollointhehouse"
        }
        meta {
            attributes["property"] = "twitter:description"
            content = "Personal website for Apollointhehouse"
        }

        meta(name = "description", content = "Personal website for Apollointhehouse")
    }

    body {
        embed {
            src = "${resource.path}"
            type = "application/pdf"
            attributes["frameborder"] = "0"
            attributes["scrolling"] = "auto"
            style = "height:100vh;width:100%"
        }
    }
}

context(config: Config)
fun fetchCVs(): List<Pair<String, ResolvedResource>> {
    val branchArtifacts = API.getLatestBuildArtifactsPerBranch("Apollointhehouse", "Apollointhehouse-CV", "Build PDF")

    return branchArtifacts.mapNotNull { [branch, artifacts] ->
        val latest = artifacts.maxByOrNull { it.createdAt } ?: return@mapNotNull null
        branch to GithubArtifactResource(name = branch, artifact = latest, savePath = "cv", ext = "pdf").resolve()
    }
}