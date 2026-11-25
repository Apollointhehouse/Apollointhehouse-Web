package me.apollointhehouse.ui.components

import kotlinx.html.*
import me.apollointhehouse.ui.html.extFxPush
import me.apollointhehouse.ui.html.fxAction
import me.apollointhehouse.ui.html.fxSwap
import me.apollointhehouse.ui.html.fxTarget

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
                        fxAction = page.fragment
                        fxTarget = "main"
                        fxSwap = "innerHTML"
                        extFxPush = true

                        href = page.route
                        +page.name
                    }
                }
            }
        }
    }
