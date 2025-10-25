package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

private data class Download(
    val name: String,
    val description: String,
    val url: String,
)

private val downloads = listOf(
    Download("Steam Installer", "Install Steam on school computers!", "https://raw.githubusercontent.com/Apollointhehouse/RLHS-Utils/refs/heads/main/steam-install.ps1"),
    Download("Minecraft Installer", "Install Minecraft using PrismLauncher on school computers!", "https://raw.githubusercontent.com/Apollointhehouse/RLHS-Utils/refs/heads/main/minecraft-install.ps1"),
)

fun HTML.rlhs() = base("RLHS Utils") {
    content {
        section("downloads-list") {
            for ((name, description, url) in downloads) {
                article {
                    a(href = url) { h2 { +name } }
                    p { +description }
                }
            }
        }
    }
}
