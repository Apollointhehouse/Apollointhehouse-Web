package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*
import me.apollointhehouse.setupRoutes
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.io.File

private fun generateBlogs(): Map<String, HTML.() -> Unit> {
    val flavour = CommonMarkFlavourDescriptor()
    val mdParser = MarkdownParser(flavour)

    val texts = File("./src/main/kotlin/blogs")
        .listFiles()
        .map { it.name.substringBefore(".md") to it.readText() }

    val routes = texts.associate { (name, text) ->
        val parsedTree = mdParser.buildMarkdownTreeFromString(text)
        val html = HtmlGenerator(text, parsedTree, flavour).generateHtml()
        val page: HTML.() -> Unit = { blog(name, html) }

        "blogs/$name" to page
    }

    setupRoutes(routes)

    return routes
}

fun HTML.blogs() = base("Blogs") {
    div("hero") {
        navbar(Page("Home", "../"), Page("Projects", "../projects"), Page("Blogs", ""))
    }
    main(classes = "container") {
        section("blogs-list") {
            val blogs = generateBlogs()

            for ((route, _) in blogs) {
                val name = route.substringAfter("/")

                article {
                    a(href = name) { h2 { +name } }
                }
            }
        }
    }
    footer()
}