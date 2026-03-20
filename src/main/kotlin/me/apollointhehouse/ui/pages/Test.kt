package me.apollointhehouse.ui.pages

import kotlinx.html.*
import me.apollointhehouse.ui.components.base

fun HTML.test() =
    base("Test") {
        script { src = "/test.js" }
    }
