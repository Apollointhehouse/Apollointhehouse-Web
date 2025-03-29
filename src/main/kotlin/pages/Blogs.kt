package me.apollointhehouse.pages

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import me.apollointhehouse.Config
import me.apollointhehouse.components.*
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.io.File

fun generateBlogs(): List<String> {
    val flavour = CommonMarkFlavourDescriptor()
    val mdParser = MarkdownParser(flavour)

    val texts = File("./src/main/kotlin/blogs").listFiles().map { it.name.substringBefore(".md") to it.readText() }

    return texts.map { (name, text) ->
        val folder =  File("${Config.base}/blogs/$name").also { it.mkdirs() }
        val file = File("$folder/index.html").also { it.createNewFile() }

        val parsedTree = mdParser.buildMarkdownTreeFromString(text)
        val html = HtmlGenerator(text, parsedTree, flavour).generateHtml()

        file.writer().use {
            it.appendLine("<!DOCTYPE html>").appendHTML().html { blog(name, html) }
        }
        name
    }
}

val blogs = generateBlogs()

fun HTML.blogs() = base("Blogs") {
    div("hero") {
        navbar(Page("Home", "../"), Page("Projects", "../projects"), Page("Blogs", ""))
    }
    main(classes = "container") {
        section("blogs-list") {
            for (name in blogs) {
                article {
                    a(href = name) { h2 { +name } }
                }
            }
        }
    }
    footer()
}