package me.apollointhehouse.components

import kotlinx.html.*

fun HTML.blog(name: String, html: String) = base(name) {
    div("hero") {
        navbar(Page("Home", "../../"), Page("Projects", "../../projects"), Page("Blogs", "../"))
    }

    main(classes = "container") {
        unsafe {
            +html.substringAfter("<body>").substringBefore("</body>")
        }
    }

    footer()
}