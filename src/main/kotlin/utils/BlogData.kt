package me.apollointhehouse.utils

import java.text.SimpleDateFormat
import java.util.*

private val format = SimpleDateFormat("yyyy-MM-dd")

data class BlogData(
    val title: String,
    val date: Date,
    val tags: List<String>,
)

fun BlogData(meta: Map<String, String>) = BlogData(
    title = meta["title"]!!,
    date = format.parse(meta["date"]!!),
    tags = meta["tags"]!!.split(","),
)