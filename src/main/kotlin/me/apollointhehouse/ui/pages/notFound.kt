package me.apollointhehouse.ui.pages

import kotlinx.html.HTML
import kotlinx.html.h3
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.html.article

fun HTML.notFound() = base("404") {
    article(id = "page-not-found") {
        h3 { +"404: Page Not Found!" }
    }
}