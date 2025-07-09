package me.apollointhehouse.components

import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.div

inline fun FlowContent.hero(crossinline block: DIV.() -> Unit = {}) = div("hero") {
    navbar(Page("Home", "/"), Page("Projects", "/projects"), Page("Blogs", "/blogs"))

    block()
}