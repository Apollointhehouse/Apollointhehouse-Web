package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

private data class Download(
    val name: String,
    val description: String,
    val url: String,
)

private val downloads = listOf(
    Download("Steam Installer", "Install Steam on school computers!", "../downloads/steam-install.ps1"),
    Download("Minecraft Installer", "Install Minecraft using PrismLauncher on school computers!", "../downloads/minecraft-install.ps1"),
)

fun HTML.rlhs() = base("RLHS Utils") {
    hero()

    main(classes = "container") {
        section("downloads-list") {
            for (download in downloads) {
                article {
                    a(href = download.url) { h2 { +download.name } }
                    p { +download.description }
                }
            }
        }
    }
    footer()
}
