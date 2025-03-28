package me.apollointhehouse.components

import kotlinx.html.*

data class Page(val name: String, val route: String)

fun FlowContent.navbar(vararg pages: Page) = nav("container-fluid") {
    ul {
        for (page in pages) {
            li {
                a {
                    href = page.route
                    +page.name
                }
            }
        }
    }
}