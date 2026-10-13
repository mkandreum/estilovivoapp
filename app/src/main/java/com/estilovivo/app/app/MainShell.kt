package com.estilovivo.app.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Checkroom
import androidx.compose.material.icons.rounded.Flight
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocalLaundryService
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.estilovivo.app.core.design.EvMotion
import com.estilovivo.app.core.design.components.EvActionBubbles
import com.estilovivo.app.core.design.components.EvBottomBar
import com.estilovivo.app.core.design.components.EvBubble
import com.estilovivo.app.core.design.components.EvNavItem
import com.estilovivo.app.feature.home.HomeTab
import com.estilovivo.app.feature.notifications.NotificationBell
import com.estilovivo.app.feature.profile.ProfileTab
import com.estilovivo.app.feature.social.SocialTab
import com.estilovivo.app.feature.wardrobe.WardrobeTab
import com.estilovivo.app.feature.wardrobe.rememberWashingCount

/**
 * Contenedor de las 4 pestañas con barra inferior flotante.
 * Tocar "Armario" despliega las burbujas Viajes / Lavar / Probar (como en la web).
 */
@Composable
fun MainShell(tab: MainTab, onTabChange: (MainTab) -> Unit) {
    val nav = LocalNavigator.current
    var bubblesOpen by remember { mutableStateOf(false) }
    val washing = rememberWashingCount()

    val items = remember(washing) {
        listOf(
            EvNavItem(MainTab.Home.id, "Inicio", Icons.Rounded.Home),
            EvNavItem(MainTab.Wardrobe.id, "Armario", Icons.Rounded.Checkroom),
            EvNavItem(MainTab.Social.id, "Social", Icons.Rounded.Groups),
            EvNavItem(MainTab.Profile.id, "Perfil", Icons.Rounded.Person),
        )
    }

    Box(Modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = tab,
            transitionSpec = {
                val dir = if (targetState.ordinal > initialState.ordinal) 1 else -1
                (slideInHorizontally(tween(380, easing = EvMotion.emphasized)) { dir * it / 5 } + fadeIn(tween(300))) togetherWith
                    (slideOutHorizontally(tween(380, easing = EvMotion.emphasized)) { -dir * it / 5 } + fadeOut(tween(160)))
            },
            label = "tabs",
            modifier = Modifier.fillMaxSize(),
        ) { current ->
            when (current) {
                MainTab.Home -> HomeTab()
                MainTab.Wardrobe -> WardrobeTab()
                MainTab.Social -> SocialTab()
                MainTab.Profile -> ProfileTab()
            }
        }

        // Campana de notificaciones (arriba a la derecha, como en la web)
        Box(Modifier.align(Alignment.TopEnd).statusBarsPadding().padding(top = 8.dp, end = 16.dp)) {
            NotificationBell(onClick = { nav.navigate(Notifications) })
        }

        EvActionBubbles(
            visible = bubblesOpen && tab == MainTab.Wardrobe,
            bubbles = listOf(
                EvBubble("Viajes", Icons.Rounded.Flight, Color(0xFF6366F1)) { nav.navigate(Suitcase) },
                EvBubble("Lavar", Icons.Rounded.LocalLaundryService, Color(0xFF3B82F6), badge = washing) { nav.navigate(Washing) },
                EvBubble("Probar", Icons.Rounded.AutoAwesome, Color(0xFFEC4899)) { nav.navigate(TryOn()) },
            ),
            onDismiss = { bubblesOpen = false },
        )

        EvBottomBar(
            items = items,
            selectedId = tab.id,
            onSelect = { id ->
                val target = MainTab.entries.first { it.id == id }
                if (target == MainTab.Wardrobe) {
                    // Primera vez: ir al armario y abrir burbujas; segunda: alternar
                    bubblesOpen = if (tab == MainTab.Wardrobe) !bubblesOpen else true
                } else {
                    bubblesOpen = false
                }
                onTabChange(target)
            },
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}
