package me.apollointhehouse.components

import kotlinx.html.*

fun FlowContent.header() {
    header(classes = "container") {
        navbar("Home" to "index.html", "Projects" to "projects.html")
    }
}