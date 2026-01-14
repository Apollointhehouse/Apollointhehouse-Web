package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

fun HTML.test() =
    base("Test") {
        script { src = "/test.js" }
    }
