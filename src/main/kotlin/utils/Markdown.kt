package me.apollointhehouse.utils

import kotlinx.html.unsafe
import me.apollointhehouse.models.Markdown
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

fun Markdown.toComponent(name: String): String {
    routing("/components/$name" to {
        unsafe {
            +toHtml()
        }
    })

    return name
}