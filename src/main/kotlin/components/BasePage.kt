package me.apollointhehouse.components

import kotlinx.html.*

inline fun HTML.base(title: String, crossinline block: BODY.() -> Unit) {
    lang = "en"

    head {
        meta(charset="utf-8")
        meta(name="viewport", content="width=device-width, initial-scale=1")
        meta(name="color-scheme", content="light dark")

        link(rel="stylesheet", href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css")
        link(rel="stylesheet", href="/style.css")
        link(rel = "icon", type = "image/x-icon", href = "/assets/images/icon.ico")

        meta(name="keywords", content="NZ, New Zealand, Apollointhehouse, Apollo, Kotlin, Backend, Developer, UOA, University of Auckland")
        meta(name="author", content="Apollointhehouse")
        meta(name="canonical", content="apollointhehouse.dev")
        title { +title }
        meta {
            attributes["property"] = "og:title"
            content = title
        }
        meta {
            attributes["property"] = "og:title"
            content = title
        }

        meta {
            attributes["property"] = "og:site_name"
            content = "Apollointhehouse"
        }

        meta {
            attributes["property"] = "og:description"
            content = "Personal website for Apollointhehouse, a hobbyist developer from New Zealand"
        }
        meta {
            attributes["property"] = "twitter:description"
            content = "Personal website for Apollointhehouse, a hobbyist developer from New Zealand"
        }

        meta(name="description", content="Personal website for Apollointhehouse, a hobbyist developer from New Zealand")

        // verifcation
        meta {
            name = "google-site-verification"
            content = "P4gfe5lTqzhiHqVu4SQL5PYaKwXKfaNCVVzWuXwZjN8"
        }
        meta {
            name = "google-site-verification"
            content = "HyKk6IN2a8kItbBpm2aqLYEJCHiJTiu_Pwt09nckkwE"
        }
    }

    body {
        script { src = "https://cdn.jsdelivr.net/npm/htmx.org@2.0.7/dist/htmx.min.js" }

        block()
    }
}