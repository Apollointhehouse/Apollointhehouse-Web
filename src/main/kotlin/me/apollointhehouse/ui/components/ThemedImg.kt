package me.apollointhehouse.ui.components

import kotlinx.html.FlowContent
import kotlinx.html.ImgLoading
import kotlinx.html.img
import kotlinx.html.picture
import kotlinx.html.source
import me.apollointhehouse.ui.components.utils.Theme

inline fun <T : Theme> FlowContent.themedImg(
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