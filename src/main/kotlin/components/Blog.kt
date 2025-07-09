package me.apollointhehouse.components

import kotlinx.html.*

fun HTML.blog(name: String, html: String) = base(name) {
    hero()

    content {
        unsafe {
            +html.substringAfter("<body>").substringBefore("</body>")
        }
    }

    footer()
}