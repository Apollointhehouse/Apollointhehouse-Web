package me.apollointhehouse.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import me.apollointhehouse.data.models.GraphQLQuery
import me.apollointhehouse.data.models.github.*
import org.apache.logging.log4j.kotlin.logger
import java.io.ByteArrayInputStream
import java.util.zip.ZipInputStream
import kotlin.io.path.Path
import kotlin.io.path.readText

object API {
    private val client: HttpClient =
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }

            install(Logging) {
                level = LogLevel.INFO
            }
        }

    private val githubQuery = Path("./src/main/resources/api/GithubQuery").readText()
    private val githubBranchesQuery = Path("./src/main/resources/api/GithubBranchesQuery").readText()

    context(config: Config)
    fun getPinnedRepos(): List<Repo> = runBlocking {
        logger.info("Getting repositories...")
        val res =
            client.post("https://api.github.com/graphql") {
                contentType(ContentType.Application.Json)
                githubAuth()

                setBody(GraphQLQuery(githubQuery))
            }

        if (!res.status.isSuccess()) {
            error("Failed to get pinned repos")
        }

        val userData = res.body<UserData>()
        userData.data.user.repositories.edges
            .map { it.node }
    }

    context(config: Config)
    fun getLatestBuildArtifactsPerBranch(
        owner: String,
        repo: String,
        workflowName: String,
    ): Map<String, List<Artifact>> = runBlocking {
        logger.info("Getting branches for $owner/$repo...")

        val variables = buildJsonObject {
            put("owner", owner)
            put("name", repo)
        }

        val res = client.post("https://api.github.com/graphql") {
            contentType(ContentType.Application.Json)
            githubAuth()
            setBody(GraphQLQuery(githubBranchesQuery, variables))
        }

        if (!res.status.isSuccess()) error("Failed to get branches")

        val body = res.body<RepoBranchesResponse>()

        val repository = body.data.repository

        val branches = repository.refs.nodes
        logger.info("Found ${branches.size} branches: ${branches.map { it.name }}")

        branches.associate { ref ->
            val allRuns = ref.target?.checkSuites?.nodes?.mapNotNull { it.workflowRun } ?: emptyList()
            logger.info("Branch '${ref.name}': ${allRuns.size} workflow runs -> ${allRuns.map { it.workflow.name }}")

            val runId = allRuns.firstOrNull { it.workflow.name == workflowName }?.databaseId
            if (runId == null) {
                logger.warn("Branch '${ref.name}': no run found for workflow '$workflowName'")
            }

            val artifacts = runId?.let { fetchArtifacts(owner, repo, it) } ?: emptyList()
            logger.info("Branch '${ref.name}': run=$runId, artifacts=${artifacts.map { it.name to it.expired }}")

            ref.name to artifacts
        }
    }

    context(config: Config)
    private suspend fun fetchArtifacts(owner: String, repo: String, runId: Long): List<Artifact> {
        val res = client.get("https://api.github.com/repos/$owner/$repo/actions/runs/$runId/artifacts") {
            githubAuth()
        }
        if (!res.status.isSuccess()) {
            logger.warn("Artifacts fetch for run $runId failed: ${res.status}")
            return emptyList()
        }
        return res.body<ArtifactsResponse>().artifacts
    }

    context(config: Config)
    suspend fun downloadArtifactBytes(artifact: Artifact): ByteArray {
        logger.info("Downloading artifact ${artifact.name}...")

        val res = client.get(artifact.archiveDownloadUrl) { githubAuth() }
        if (!res.status.isSuccess()) {
            error("Failed to download artifact ${artifact.name}")
        }

        val zipBytes = res.body<ByteArray>()

        ZipInputStream(ByteArrayInputStream(zipBytes)).use { zip ->
            var entry = zip.nextEntry
            while (entry != null) {
                if (entry.name.endsWith(".pdf", ignoreCase = true)) {
                    return zip.readBytes()
                }
                entry = zip.nextEntry
            }
        }

        error("No PDF found in artifact ${artifact.name}")
    }

    context(config: Config)
    private fun HttpRequestBuilder.githubAuth() {
        headers {
            append(HttpHeaders.Authorization, "bearer ${config.token}")
            append(HttpHeaders.UserAgent, "Ktor Client")
        }
    }
}
