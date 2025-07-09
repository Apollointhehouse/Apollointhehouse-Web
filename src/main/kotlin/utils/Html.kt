package me.apollointhehouse.utils

import kotlinx.html.*
import kotlinx.html.attributes.enumEncode
import kotlin.contracts.ExperimentalContracts

@HtmlTagMarker
@OptIn(ExperimentalContracts::class)
inline fun FlowOrInteractiveOrPhrasingContent.iframe(
    sandbox : IframeSandbox? = null,
    classes : String? = null,
    loading: String? = null,
    name: String? = null,
    ariaBusy: Boolean? = null,
    crossinline block : IFRAME.() -> Unit = {}
) {
    IFRAME(attributesMapOf(
        "sandbox", sandbox?.enumEncode(),
        "class", classes,
        "loading", loading,
        "name", name,
        "aria-busy", ariaBusy?.toString()
        ), consumer
    ).visit(block)
}

fun FlowContent.loadComponent(src: String) =
    div {
        attributes["aria-busy"] = "true"
        iframe(loading = "lazy") {
            this.src = "../$src"
            width = "0"
            height = "0"
            onLoad = "this.parentNode.setAttribute(\"aria-busy\", \"false\");this.replaceWith(...this.contentDocument.body.childNodes);"
        }
    }