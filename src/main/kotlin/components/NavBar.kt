package me.apollointhehouse.components

import kotlinx.html.*

// name to route
typealias Page = Pair<String, String>

fun FlowContent.navbar(vararg pages: Page) {
    nav {
        ul {
            for ((name, route) in pages) {
                li {
                    a {
                        href = route
                        +name
                    }
                }
            }
        }
    }
}