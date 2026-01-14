package me.apollointhehouse.utils

import kotlinx.html.*
import kotlin.contracts.ExperimentalContracts

@HtmlTagMarker
@OptIn(ExperimentalContracts::class)
inline fun SectioningOrFlowContent.article(
    classes: String? = null,
    id: String? = null,
    crossinline block: ARTICLE.() -> Unit = {},
) {
    ARTICLE(attributesMapOf("class", classes, "id", id), consumer).visit(block)
}

inline fun <T : Theme> FlowContent.themeImg(
    themes: Iterable<T>,
    name: String = "",
    crossinline block: (T) -> String,
) {
    picture {
        for (theme in themes) {
            source {
                media = "(prefers-color-scheme: ${theme.mode})"
                srcset = block(theme)
            }
        }
        img {
            src = "#"
            alt = name
            loading = ImgLoading.lazy
        }
    }
}
