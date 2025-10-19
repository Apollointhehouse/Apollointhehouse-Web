package me.apollointhehouse.components

import kotlinx.html.*
import me.apollointhehouse.utils.hxBoost

data class Page(val name: String, val route: String)

fun FlowContent.navbar(vararg pages: Page) = nav("container-fluid") {
    ul {
        li {
            strong { +"Apollointhehouse" }
        }
    }

    ul {
        hxBoost = true

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