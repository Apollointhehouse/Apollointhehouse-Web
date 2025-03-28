package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

fun HTML.blogs() = base("Blogs") {
    div("hero") {
        navbar(Page("Home", "../"), Page("Projects", "../projects"), Page("Blogs", ""))
    }
    main(classes = "container") {
        h1 { +"Work in Progress!" }
    }
    footer()
}