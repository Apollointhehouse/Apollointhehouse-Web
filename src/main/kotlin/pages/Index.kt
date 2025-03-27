package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

fun HTML.index() = base("Home") {
    hGroup {
        p { +"Hello!" }
        h1 { +"I'm Apollo!" }
        h2 { +"Kotlin Enthusiast from New Zealand" }
    }

//    h1 { +"Hello World!" }
}