package me.apollointhehouse.ui.pages

import kotlinx.html.HTML
import kotlinx.html.h3
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.components.content
import me.apollointhehouse.ui.components.utils.article

fun HTML.notFound() = base("404") {
    content {
        article(id = "page-not-found") {
            h3 { +"404: Page Not Found!" }
        }
    }
}