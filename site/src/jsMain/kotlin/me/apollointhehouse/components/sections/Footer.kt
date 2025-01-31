package me.apollointhehouse.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.navigation.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.apollointhehouse.toSitePalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Span

val FooterStyle = CssStyle.base {
    Modifier
        .backgroundColor(colorMode.toSitePalette().nearBackground)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

@Composable
fun Footer(modifier: Modifier = Modifier) =
    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
            val sitePalette = ColorMode.current.toSitePalette()
            SpanText("Built with ")
            Link(
                "https://github.com/varabyte/kobweb",
                "Kobweb",
                Modifier.setVariable(ColorVar, sitePalette.brand.primary),
                variant = UncoloredLinkVariant
            )
        }
    }
