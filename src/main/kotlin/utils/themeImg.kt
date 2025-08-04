package me.apollointhehouse.utils

import kotlinx.html.FlowContent
import kotlinx.html.ImgLoading
import kotlinx.html.SOURCE
import kotlinx.html.img
import kotlinx.html.picture
import kotlinx.html.source

fun <T> FlowContent.themeImg(themes: Iterable<T>, alt: String = "", block:  SOURCE.(T) -> Unit) {
    picture {
        for (theme in themes) {
            source {
                block(theme)
            }
        }
        img {
            src = "#"
            this@img.alt = alt
            loading = ImgLoading.lazy
        }
    }
}