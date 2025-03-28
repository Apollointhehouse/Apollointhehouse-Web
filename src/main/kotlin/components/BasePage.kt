package me.apollointhehouse.components

import kotlinx.html.*

fun HTML.base(title: String, block: BODY.() -> Unit) {
    lang = "en"

    head {
        meta(charset="utf-8")
        meta(name="viewport", content="width=device-width, initial-scale=1")
        meta(name="color-scheme", content="light dark")

        link(rel="stylesheet", href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css")
        link(rel="stylesheet", href="style.css")
        title { +title }
    }

    body {
        block()
    }
}