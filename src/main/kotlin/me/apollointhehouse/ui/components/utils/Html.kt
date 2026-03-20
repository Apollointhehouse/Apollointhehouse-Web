package me.apollointhehouse.ui.components.utils

import kotlinx.html.*

@HtmlTagMarker
inline fun SectioningOrFlowContent.article(
    classes: String? = null,
    id: String? = null,
    crossinline block: ARTICLE.() -> Unit = {},
) {
    ARTICLE(attributesMapOf("class", classes, "id", id), consumer).visit(block)
}

