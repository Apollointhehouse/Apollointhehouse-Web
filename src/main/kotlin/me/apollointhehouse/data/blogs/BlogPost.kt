package me.apollointhehouse.data.blogs

import me.apollointhehouse.data.models.Markdown
import me.apollointhehouse.data.logger
import org.slf4j.Logger
import java.io.File

data class BlogPost(
    val meta: BlogData,
    val slug: String,
    val html: String,
) {
    companion object {
        private val logger: Logger = logger()

        operator fun invoke(text: String): BlogPost {
            logger.info("Parsing blog post...")
            val lines = text.lines()
            val separatorIndex = lines.indexOfFirst { it == "---" }

            require(separatorIndex >= 0) { "Missing blog metadata separator" }

            val meta =
                lines
                    .take(separatorIndex)
                    .filter { it.isNotBlank() }
                    .associate { line ->
                        val keyValueIndex = line.indexOf("=")
                        require(keyValueIndex > 0) { "Invalid blog metadata line: $line" }

                        line.substring(0, keyValueIndex).trim() to line.substring(keyValueIndex + 1).trim()
                    }
                    .let { BlogData(it) }

            val markdown =
                lines
                    .drop(separatorIndex + 1)
                    .joinToString("\n")

            return BlogPost(
                meta = meta,
                slug = slugify(meta.title),
                html = Markdown(markdown).toHtml(),
            )
        }
    }
}

fun loadBlogPosts(root: File = File("./src/main/resources/blogs")): List<BlogPost> =
    root
        .walkTopDown()
        .filter { it.isFile && it.extension == "md" }
        .map { BlogPost(it.readText()) }
        .toList()
        .also { posts ->
            val duplicateSlugs =
                posts
                    .groupBy { it.slug }
                    .filterValues { it.size > 1 }
                    .keys

            require(duplicateSlugs.isEmpty()) { "Duplicate blog slugs: ${duplicateSlugs.joinToString()}" }
        }

fun slugify(title: String): String =
    title
        .trim()
        .lowercase()
        .replace(Regex("[^a-z0-9]+"), "-")
        .trim('-')
