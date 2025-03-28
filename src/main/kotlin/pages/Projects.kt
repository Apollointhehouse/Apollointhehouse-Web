package me.apollointhehouse.pages

import kotlinx.html.*
import me.apollointhehouse.components.*

data class Project(
    val name: String,
    val description: String,
    val link: String,
)

// TODO: replace this with github API call to get pinned repos and auto make list
val projects: List<Project> = listOf(
    Project("SyncBot", "A bot that lets you sync messages from channels on different discord servers", "https://github.com/Apollointhehouse/SyncBot"),
    Project("TypoType", "Where Your Typing Skills Go to Die", "https://github.com/Apollointhehouse/TypoType"),
    Project("Raywire", "My WIP event library written in kotlin!", "https://github.com/Apollointhehouse/Raywire"),
    Project("Apollointhehouse-Web", "My personal website!", "https://github.com/Apollointhehouse/Apollointhehouse-Web"),
    Project("IsekaiGame", "WIP story driven RPG, written in kotlin", "https://github.com/Olypolyu/IsekaiGame"),
    Project("aoc-2024-kotlin", "My (bad) attempt at completing AOC 2024 in kotlin", "https://github.com/Apollointhehouse/aoc-2024-kotlin")
)

fun HTML.projects() = base("Projects") {
    div("hero") {
        navbar(Page("Home", "../"), Page("Projects", ""), Page("Blogs", "../blogs"))
    }
    main(classes = "container") {
        for (project in projects) {
            section("projects-list") {
                article {
                    div("badges") {
                        a(href = project.link) { h2 { +project.name } }
                        p { +project.description }
                    }
                }
            }
        }
    }
    footer()
}