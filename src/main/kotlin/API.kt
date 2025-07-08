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
import me.apollointhehouse.models.RepoHolder
import me.apollointhehouse.models.UserData

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
      search(first: 100, type: REPOSITORY, query: "topic:show-project user:Apollointhehouse") {
        pageInfo {
          hasNextPage
          endCursor
          }
        repos: edges {
          repo: node {
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

fun getPinnedRepos(): List<RepoHolder>? = runBlocking {
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
        res.data.search.repos
    } else null
}