package me.apollointhehouse.data.blogs

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BlogPostTest {
    @Test
    fun `BlogPost function maps metadata body and slug`() {
        val post =
            BlogPost(
                """
                title=My First Blog!
                date=2026-05-01
                tags=kotlin, static sites
                ---
                # Hello
                """.trimIndent(),
            )

        assertEquals("My First Blog!", post.meta.title)
        assertEquals("2026-05-01", post.meta.date.toString())
        assertEquals(listOf("kotlin", "static sites"), post.meta.tags)
        assertEquals("my-first-blog", post.slug)
        assertContains(post.html, "<h1")
        assertContains(post.html, "Hello")
    }

    @Test
    fun `BlogPost function rejects missing metadata separator`() {
        assertFailsWith<IllegalArgumentException> {
            BlogPost("title=Missing Separator")
        }
    }

    @Test
    fun `slugify normalizes whitespace and punctuation`() {
        assertEquals("a-blog-post-2026", slugify("  A Blog Post: 2026!  "))
    }
}
