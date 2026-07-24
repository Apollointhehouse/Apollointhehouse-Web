package me.apollointhehouse.ui.pages

import kotlinx.html.HTML
import kotlinx.html.h3
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.resources.Resources
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.html.article

context(_: Resources, _: Config)
fun HTML.notFound() = base("404") {
    article(id = "page-not-found") {
        h3 { +"404: Page Not Found!" }
    }
}