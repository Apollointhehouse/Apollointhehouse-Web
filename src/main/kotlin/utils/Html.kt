package me.apollointhehouse.utils

import kotlinx.html.*
import kotlin.contracts.ExperimentalContracts

@HtmlTagMarker
@OptIn(ExperimentalContracts::class)
inline fun FlowOrInteractiveContent.details(classes: String? = null, name: String, crossinline block : DETAILS.() -> Unit = {}) {
    DETAILS(attributesMapOf("class", classes, "name", name), consumer).visit(block)
}