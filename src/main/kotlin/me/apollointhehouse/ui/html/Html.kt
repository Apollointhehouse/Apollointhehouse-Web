package me.apollointhehouse.ui.html

import kotlinx.html.*

@HtmlTagMarker
inline fun SectioningOrFlowContent.article(
    classes: String? = null,
    id: String? = null,
    crossinline block: ARTICLE.() -> Unit = {},
) {
    ARTICLE(attributesMapOf("class", classes, "id", id), consumer).visit(block)
}

@HtmlTagMarker
inline fun SectioningOrFlowContent.section(
    classes: String? = null,
    id: String? = null,
    crossinline block: SECTION.() -> Unit = {},
) {
    SECTION(attributesMapOf("class", classes, "id", id), consumer).visit(block)
}

@HtmlTagMarker
inline fun FlowContent.title(crossinline block : TITLE.() -> Unit = {}) {
    TITLE(emptyMap, consumer).visit(block)
}

fun FlowContent.unsafe(block: Unsafe.() -> Unit): Unit = consumer.onTagContentUnsafe(block)