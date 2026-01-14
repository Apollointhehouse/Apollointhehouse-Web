package me.apollointhehouse.components

import kotlinx.html.FlowContent
import kotlinx.html.MAIN
import kotlinx.html.main
import kotlinx.html.style

inline fun FlowContent.content(crossinline block: MAIN.() -> Unit) =
    main(classes = "container") {
        style = "min-height: 100vh;"
        block()
    }
