package me.apollointhehouse.data.routing.types

import kotlinx.html.FlowContent
import me.apollointhehouse.data.Config
import me.apollointhehouse.data.resources.Resources
import me.apollointhehouse.data.routing.Router
import me.apollointhehouse.data.routing.types.PageRoute.Companion.page
import me.apollointhehouse.ui.components.base
import me.apollointhehouse.ui.html.createFragment
import me.apollointhehouse.ui.html.hxGet
import me.apollointhehouse.ui.html.hxSwap
import me.apollointhehouse.ui.html.hxTrigger
import org.apache.logging.log4j.kotlin.logger
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.div
import kotlin.io.path.writeText

class FragmentRoute private constructor(
    private val route: String,
    private val fragment: FlowContent.() -> Unit
) : Route {
    private val logger = logger()

    context(config: Config)
    override fun create() {
        logger.info("Creating Fragment: /fragment$route")
        val fragment = createFragment {
            fragment()
        }

        val path = Path.of("${config.fragment}/$route")
        path.createDirectories()
        (path / "index.html")
            .also { it.createFile() }
            .writeText(fragment)
    }

    companion object {
        context(builder: Router.Builder, _: Config, _: Resources)
        fun fragment(route: String, block: FlowContent.() -> Unit) {
            builder.route(FragmentRoute(route, block))

            page(route) {
                base {
                    hxGet = "/fragment$route"
                    hxSwap = "transition:true settle:1ms"
                    hxTrigger = "load"
                }
            }
        }
    }
}