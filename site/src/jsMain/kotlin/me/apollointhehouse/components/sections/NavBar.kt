package me.apollointhehouse.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.*
import com.varabyte.kobweb.silk.components.navigation.*
import com.varabyte.kobweb.silk.components.overlay.*
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.apollointhehouse.components.widgets.IconButton
import org.jetbrains.compose.web.css.cssRem

val NavBarStyle = CssStyle.base {
    Modifier.fillMaxWidth().padding(1.cssRem)
}

@Composable
private fun NavLink(path: String, text: String) =
    Link(path, text, variant = UndecoratedLinkVariant.then(UncoloredLinkVariant))

@Composable
private fun MenuItems() {
    NavLink("/", "Home")
    NavLink("/about", "About")
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    IconButton(onClick = { colorMode = colorMode.opposite },) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
    Tooltip(ElementTarget.PreviousSibling, "Toggle color mode", placement = PopupPlacement.BottomRight)
}

@Composable
private fun HamburgerButton(onClick: () -> Unit) =
    IconButton(onClick) {
        HamburgerIcon()
    }

@Composable
private fun CloseButton(onClick: () -> Unit) =
    IconButton(onClick) {
        CloseIcon()
    }

@Composable
fun NavBar() =
    Row(NavBarStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {

    }