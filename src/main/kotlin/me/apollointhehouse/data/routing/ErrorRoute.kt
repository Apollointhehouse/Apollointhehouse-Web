package me.apollointhehouse.data.routing

import io.ktor.http.HttpStatusCode
import me.apollointhehouse.data.Config
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createFile

class ErrorRoute private constructor(
    val code: HttpStatusCode,
    override val url: String,
    override val page: Page
) : Route {
    override fun create(): Path {
        return Path("${Config.base}/${code.value}.html").also { it.createFile() }
    }

    companion object : RouteFactory<HttpStatusCode> {
        override infix fun HttpStatusCode.bind(page: Page): ErrorRoute = ErrorRoute(this, this.value.toString(), page)
    }
}