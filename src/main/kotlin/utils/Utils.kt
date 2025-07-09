package me.apollointhehouse.utils

import kotlinx.html.HTML
import kotlinx.html.unsafe
import me.apollointhehouse.components.base
import me.apollointhehouse.setupRoutes
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

private val flavour = CommonMarkFlavourDescriptor()
private val mdParser = MarkdownParser(flavour)

fun Markdown.toHtml(): String {
    val parsedTree = mdParser.buildMarkdownTreeFromString(value)
    val html = HtmlGenerator(value, parsedTree, flavour).generateHtml()

    return html
}

fun component(name: String, html: String): String {
    val page: HTML.() -> Unit = {
        base(name) {
            unsafe {
                +html.substringAfter("<body>").substringBefore("</body>")
            }
        }
    }

    setupRoutes(mapOf(
        "/components/$name" to page
    ))

    return "/components/$name/index.html"
}

data class Markdown(val value: String)