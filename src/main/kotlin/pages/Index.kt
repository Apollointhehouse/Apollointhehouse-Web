package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

fun HTML.index() = base("Home") {
    div("hero") {
        navbar(Page("Home", ""), Page("Projects", "projects"), Page("Blogs", "blogs"))

        div("container") {
            hGroup {
                p { +"Hello!" }
                h1 { +"I'm Apollo!" }
                h2 { +"Kotlin Enthusiast from New Zealand" }
            }
        }
    }
    main(classes = "container") {
        h1 { +"Work in Progress!" }
    }
    footer()
}