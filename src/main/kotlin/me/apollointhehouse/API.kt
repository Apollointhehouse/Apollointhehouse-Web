package me.apollointhehouse

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
import me.apollointhehouse.models.GraphQLQuery
import models.v2.Repo
import models.v2.UserData
import java.io.File

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
            level = LogLevel.ALL
        }
    }

private val githubQuery = File("./src/main/resources/api/GithubQuery").readText()

fun getPinnedRepos(): List<Repo>? =
    runBlocking {
        val res =
            client.post("https://api.github.com/graphql") {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "bearer ${Config.pat}")
                    append(HttpHeaders.UserAgent, "Ktor Client")
                }

                setBody(GraphQLQuery(githubQuery))
            }

        if (res.status.isSuccess()) {
            val res = res.body<UserData>()
            res.data.user.repositories.edges
                .map { it.node }
        } else {
            null
        }
    }
