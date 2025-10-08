package me.apollointhehouse.utils

import kotlinx.html.HTML
import kotlinx.html.unsafe
import me.apollointhehouse.components.componentBase
import me.apollointhehouse.models.Markdown
import me.apollointhehouse.setupRoutes
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

private val flavour = CommonMarkFlavourDescriptor()
private val mdParser = MarkdownParser(flavour)

fun Markdown.toHtml(): String {
    val parsedTree = mdParser.buildMarkdownTreeFromString(text)
    val html = HtmlGenerator(text, parsedTree, flavour).generateHtml()

    return html
}

fun Markdown.toComponent(name: String): String
    = createComponent(name, this)

fun createComponent(name: String, md: Markdown): String =
    createComponent(name, md.toHtml())

fun createComponent(name: String, html: String): String {
    val page: HTML.() -> Unit = {
        componentBase(name) {
            unsafe {
                +html.substringAfter("<body>").substringBefore("</body>")
            }
        }
    }

    setupRoutes("/components/$name" to page)

    return name
}