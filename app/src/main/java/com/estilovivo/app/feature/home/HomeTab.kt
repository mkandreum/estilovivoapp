package com.estilovivo.app.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.estilovivo.app.core.design.components.EvEmptyState

/** HUECO: este fichero lo sustituye el módulo `home`. */
@Composable
fun HomeTab() {
    Box(Modifier.fillMaxSize().statusBarsPadding(), contentAlignment = Alignment.Center) {
        EvEmptyState(Icons.Rounded.Home, "Inicio", "Pantalla en construcción.")
    }
}
