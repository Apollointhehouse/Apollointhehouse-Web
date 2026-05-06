package me.apollointhehouse.data.routing

import me.apollointhehouse.data.Config
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile

class PageRoute private constructor(
    override val url: String,
    override val page: Page
) : Route {
    override fun create(): Path {
        val folder = Path.of("${Config.base}/$url").also { it.createDirectories() }
        return Path("$folder/index.html").also { it.createFile() }
    }

    companion object : RouteFactory<String> {
        context(builder: Router.Builder)
        override infix fun String.bind(page: Page): Route {
            val route = PageRoute(this, page)
            builder.addRoute(route)
            return route
        }
    }
}