package me.apollointhehouse.ui.components

import kotlinx.html.*
import me.apollointhehouse.ui.components.utils.hxGet
import me.apollointhehouse.ui.components.utils.hxPushUrl
import me.apollointhehouse.ui.components.utils.hxSwap
import me.apollointhehouse.ui.components.utils.hxTarget

data class Page(
    val name: String,
    val route: String,
) {
    val partial = "/partials$route"
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
                        hxGet = page.partial
                        hxTarget = "main"
                        hxSwap = "innerHTML show:none"
                        hxPushUrl = page.route

                        attributes["preload"] = ""
                        href = page.route
                        +page.name
                    }
                }
            }
        }
    }
