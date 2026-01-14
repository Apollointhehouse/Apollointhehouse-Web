package me.apollointhehouse.components

import kotlinx.html.*

fun HTML.blog(
    name: String,
    html: String,
) = base(name) {
    content {
        unsafe {
            +html.substringAfter("<body>").substringBefore("</body>")
        }
    }
}
