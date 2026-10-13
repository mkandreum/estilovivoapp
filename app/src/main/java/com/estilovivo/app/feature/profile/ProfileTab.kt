package com.estilovivo.app.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.estilovivo.app.core.design.components.EvEmptyState

/** HUECO: este fichero lo sustituye el módulo `profile`. */
@Composable
fun ProfileTab() {
    Box(Modifier.fillMaxSize().statusBarsPadding(), contentAlignment = Alignment.Center) {
        EvEmptyState(Icons.Rounded.Person, "Perfil", "Pantalla en construcción.")
    }
}
