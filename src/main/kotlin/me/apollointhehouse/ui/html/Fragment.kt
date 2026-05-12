package me.apollointhehouse.ui.html

import kotlinx.html.FlowContent
import kotlinx.html.TagConsumer
import kotlinx.html.stream.appendHTML

private class FlowContentFragment(override val consumer: TagConsumer<*>) : FlowContent {
    override val attributes: MutableMap<String, String> = mutableMapOf()
    override val attributesEntries: Collection<Map.Entry<String, String>>
        get() = attributes.entries
    override val emptyTag: Boolean = false
    override val inlineTag: Boolean = false
    override val namespace: String? = null
    override val tagName: String = ""
}

fun createFragment(
    block: FlowContent.() -> Unit
) = buildString {
    FlowContentFragment(appendHTML()).block()
}