package me.apollointhehouse.ui.components

import kotlinx.html.*
import me.apollointhehouse.ui.html.unsafe

fun FlowContent.blog(
    html: String,
) {
    unsafe {
        raw(html)
    }
}
