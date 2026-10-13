package com.estilovivo.app.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Construction
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.estilovivo.app.app.LocalNavigator
import com.estilovivo.app.core.design.components.EvEmptyState

/** HUECO temporal. */
@Composable
internal fun AuthPlaceholder(title: String) {
    val nav = LocalNavigator.current
    Box(Modifier.fillMaxSize().statusBarsPadding(), contentAlignment = Alignment.Center) {
        EvEmptyState(Icons.Rounded.Construction, title, "Pantalla en construcción.", actionText = "Entrar a la app", onAction = { nav.enterMain() })
    }
}
