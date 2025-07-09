package me.apollointhehouse.components

import kotlinx.html.*

fun HTML.blog(name: String, html: String) = base(name) {
    hero()

    main(classes = "container") {
        unsafe {
            +html.substringAfter("<body>").substringBefore("</body>")
        }
    }

    footer()
}