package me.apollointhehouse.components

import kotlinx.html.*

fun FlowContent.footer() {
    footer("container") {
        div("social") {
            p { +"Contact me:" }
            a {
                href = "https://github.com/apollointhehouse"
                target = "_blank"
                img(classes = "dark-mode") {
                    src = "images/github.png"
                    alt = "Github"
                }
            }
            a {
                href = "https://www.linkedin.com/in/apollointhehouse"
                target = "_blank"
                img {
                    src = "images/linkedin.png"
                    alt = "LinkedIn"
                }
            }
        }
    }
}