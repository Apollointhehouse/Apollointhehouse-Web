package me.apollointhehouse.data.blogs

import java.time.LocalDate

data class BlogData(
    val title: String,
    val date: LocalDate,
    val tags: List<String>,
) {
    companion object {
        operator fun invoke(meta: Map<String, String>): BlogData {
            val title = requireNotNull(meta["title"]) { "Missing blog title" }
            val date = requireNotNull(meta["date"]) { "Missing blog date" }
            val tags = requireNotNull(meta["tags"]) { "Missing blog tags" }

            return BlogData(
                title = title,
                date = LocalDate.parse(date),
                tags = tags.split(",").map { it.trim() }.filter { it.isNotEmpty() },
            )
        }
    }
}
