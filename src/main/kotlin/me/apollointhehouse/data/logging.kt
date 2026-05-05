package me.apollointhehouse.data

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)
fun logger(block: () -> Unit): Logger = logger(name(block))
fun logger(name: String): Logger = LoggerFactory.getLogger(name)

private fun name(block: () -> Unit): String = block::class.java.name
        .substringBefore("$")