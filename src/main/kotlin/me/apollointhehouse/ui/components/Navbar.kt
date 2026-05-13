package me.apollointhehouse.ui.components

import kotlinx.html.*
import me.apollointhehouse.ui.html.hxGet
import me.apollointhehouse.ui.html.hxPushUrl
import me.apollointhehouse.ui.html.hxSwap
import me.apollointhehouse.ui.html.hxTarget

data class Page(
    val name: String,
    val route: String,
) {
    val fragment = "/fragment$route"
}

fun FlowContent.navbar(vararg pages: Page) =
    nav("container-fluid") {
        ul {
            li {
                strong { +"Apollointhehouse" }
            }
        }

        ul {
            for (page in pages) {
                li {
                    a {
                        hxGet = page.fragment
                        hxTarget = "main"
                        hxSwap = "innerHTML show:none"
                        hxPushUrl = page.route

                        attributes["preload"] = "mouseover"
                        href = page.route
                        +page.name
                    }
                }
            }
        }
    }
