package me.apollointhehouse.ui.pages

import me.apollointhehouse.data.models.github.Repo
import me.apollointhehouse.data.models.github.RepositoryTopics
import me.apollointhehouse.data.models.github.Topic
import me.apollointhehouse.data.models.github.TopicHolder
import kotlin.test.Test
import kotlin.test.assertEquals

class ProjectsTest {
    @Test
    fun `visibleProjects keeps tagged repos and sorts by stars`() {
        val hidden = repo(name = "hidden", topics = listOf("kotlin"), stars = 50)
        val low = repo(name = "low", topics = listOf("show-project"), stars = 1)
        val high = repo(name = "high", topics = listOf("show-project"), stars = 10)

        val projects = visibleProjects(listOf(hidden, low, high))

        assertEquals(listOf("high", "low"), projects.map { it.name })
    }

    @Test
    fun `createProject maps null description to empty string`() {
        val project = createProject(repo(description = null))

        assertEquals("", project.description)
    }

    private fun repo(
        name: String = "repo",
        description: String? = "description",
        topics: List<String> = listOf("show-project"),
        stars: Int = 0,
    ) = Repo(
        name = name,
        description = description,
        url = "https://example.com/$name",
        stargazerCount = stars,
        repositoryTopics = RepositoryTopics(
            nodes = topics.map { topic -> TopicHolder(topic = Topic(topic), url = null) },
        ),
    )
}
