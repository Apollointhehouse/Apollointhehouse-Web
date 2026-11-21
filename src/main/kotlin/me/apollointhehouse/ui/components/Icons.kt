package me.apollointhehouse.ui.components

import java.util.Base64
import kotlin.io.path.Path
import kotlin.io.path.readBytes
import kotlin.io.path.readText

private val iconDir = Path("./src/main/resources/icons")

fun inlineSvg(name: String): String = iconDir.resolve("$name.svg").readText()

val faviconDataUri: String by lazy {
    "data:image/png;base64," + Base64.getEncoder().encodeToString(iconDir.resolve("favicon.png").readBytes())
}
