package me.apollointhehouse.utils

import java.text.SimpleDateFormat
import java.util.*

private val format = SimpleDateFormat("yyyy-MM-dd")

data class BlogMeta(
    val title: String,
    val date: Date,
    val tags: List<String>,
)

fun BlogMeta(meta: Map<String, String>): BlogMeta =
    BlogMeta(
        title = meta["title"]!!,
        date = format.parse(meta["date"]!!),
        tags = meta["tags"]!!.split(","),
    )