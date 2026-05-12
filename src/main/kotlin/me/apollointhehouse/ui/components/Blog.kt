package me.apollointhehouse.ui.components

import kotlinx.html.*

fun HTML.blog(
    name: String,
    html: String,
) = base(name) {
    unsafe {
        raw(html)
    }
}
