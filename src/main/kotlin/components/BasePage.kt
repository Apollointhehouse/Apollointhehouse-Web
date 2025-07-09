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
        link(rel = "icon", type = "image/x-icon", href = "/assets/images/icon.png")

        meta(name="description", content="A personal website for Apollointhehouse")
        meta(name="keywords", content="NZ, New Zealand, Apollointhehouse, Apollo, Kotlin, Backend, Developer, UOA, University of Auckland")
        meta(name="author", content="Apollointhehouse")
        meta(name="canonical", content="apollointhehouse.me")
        title { +title }
    }

    body {
        block()
    }
}

inline fun HTML.componentBase(title: String, crossinline block: BODY.() -> Unit) {
    lang = "en"

    head {
        title { +title }
    }

    body {
        block()
    }
}