package me.apollointhehouse.data.models

import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

data class Markdown(
    val text: String,
) {
    private val flavour = CommonMarkFlavourDescriptor()
    private val mdParser = MarkdownParser(flavour)

    fun toHtml(): String {
        val parsedTree = mdParser.buildMarkdownTreeFromString(text).children[0]
        return HtmlGenerator(text, parsedTree, flavour)
            .generateHtml()
    }
}
