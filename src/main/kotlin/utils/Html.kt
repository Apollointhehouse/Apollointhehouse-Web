package me.apollointhehouse.utils

import kotlinx.html.*
import kotlin.contracts.ExperimentalContracts

@HtmlTagMarker
@OptIn(ExperimentalContracts::class)
inline fun FlowOrInteractiveContent.details(classes: String? = null, name: String, crossinline block : DETAILS.() -> Unit = {}) {
    DETAILS(attributesMapOf("class", classes, "name", name), consumer).visit(block)
}

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