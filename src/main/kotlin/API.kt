package me.apollointhehouse

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import me.apollointhehouse.models.GraphQLQuery
import models.Node
import models.UserData

private val client: HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    install(Logging) {
        level = LogLevel.ALL
    }
}

private val query = """
    {
      user(login: "Apollointhehouse") {
        pinnedItems(first: 6, types: REPOSITORY) {
          nodes {
            ... on Repository {
                name
                description
                url
            }
          }
        }
      }
    }
""".trimIndent()

fun getPinnedRepos(): List<Node>? = runBlocking {
    val res = client.post("https://api.github.com/graphql") {
        contentType(ContentType.Application.Json)
        headers {
            append(HttpHeaders.Authorization, "bearer ${Config.pat}")
            append(HttpHeaders.UserAgent, "Ktor Client")
        }

        setBody(GraphQLQuery(query))
    }

    if (res.status.isSuccess()) {
        val res = res.body<UserData>()
        res.data.user.pinnedItems.nodes
    } else null
}