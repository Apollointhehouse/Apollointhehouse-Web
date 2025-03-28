package me.apollointhehouse.pages

import kotlinx.html.HTML
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.main
import me.apollointhehouse.components.*

fun HTML.projects() = base("Projects") {
    div("hero") {
        navbar(Page("Home", "../"), Page("Projects", ""))
    }
    main(classes = "container") {
        h1 { +"Work in Progress!" }
    }
    footer()
}