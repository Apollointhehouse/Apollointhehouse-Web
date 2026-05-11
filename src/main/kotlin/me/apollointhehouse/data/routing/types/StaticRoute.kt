@file:OptIn(ExperimentalPathApi::class)
package me.apollointhehouse.data.routing.types

import me.apollointhehouse.data.Config
import me.apollointhehouse.data.logger
import me.apollointhehouse.data.routing.Router
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.copyToRecursively

class StaticRoute private constructor(
    private val path: Path,
) : Route {
    private val logger = logger()

    override fun create() {
        logger.info("Creating static route:")

        path.copyToRecursively(target = Config.base, followLinks = false, overwrite = true)
    }

    companion object {
        context(builder: Router.Builder)
        fun static(path: Path = Path("./src/main/resources/static")): Route {
            val route = StaticRoute(path)
            builder.route(route)
            return route
        }
    }
}