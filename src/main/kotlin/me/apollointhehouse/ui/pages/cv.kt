package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.data.Resources
import kotlin.io.path.readText

fun HTML.cv() {
    lang = "en"

    head {
        meta(charset = "utf-8")
        meta(name = "viewport", content = "width=device-width, initial-scale=1")
        meta(name = "color-scheme", content = "light dark")

        style {
            unsafe {
                raw(Resources.picoCSS.path.readText())
            }
        }

        style {
            unsafe {
                raw(Resources.styleCSS.path.readText())
            }
        }
        link(rel = "icon", type = "image/x-icon", href = "/assets/images/icon.ico")

        meta(
            name = "keywords",
            content = "NZ, New Zealand, Apollointhehouse, Apollo, Kotlin, Backend, Developer, UOA, University of Auckland",
        )
        meta(name = "author", content = "Apollointhehouse")
        meta(name = "canonical", content = "apollointhehouse.dev")
        title { +"CV" }
        meta {
            attributes["property"] = "og:title"
            content = "CV"
        }
        meta {
            attributes["property"] = "og:title"
            content = "CV"
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
            src = "/CV-2026-Apollo-Cameron-Boot.pdf"
            type = "application/pdf"
            attributes["frameborder"] = "0"
            attributes["scrolling"] = "auto"
            style = "height:100vh;width:100%"
        }
    }
}