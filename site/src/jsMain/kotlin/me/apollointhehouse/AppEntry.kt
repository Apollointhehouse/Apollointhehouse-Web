package me.apollointhehouse

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.*
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.*
import org.jetbrains.compose.web.css.vh

private const val COLOR_MODE_KEY = "apollointhehouse:colorMode"

@InitSilk
fun initColorMode(ctx: InitSilkContext) = with(ctx.config) {
    initialColorMode = ColorMode.loadFromLocalStorage(COLOR_MODE_KEY) ?: ColorMode.systemPreference
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) = SilkApp {
    val colorMode = ColorMode.current
    LaunchedEffect(colorMode) {
        colorMode.saveToLocalStorage(COLOR_MODE_KEY)
    }

    Surface(
        SmoothColorStyle.toModifier()
            .minHeight(100.vh)
            .scrollBehavior(ScrollBehavior.Smooth)
    ) {
        content()
    }
}
