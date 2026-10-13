package com.estilovivo.app.feature.tryon

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.estilovivo.app.app.TryOn
import com.estilovivo.app.app.SharedScope

/** HUECO: el módulo `tryon` registra aquí sus destinos. */
fun NavGraphBuilder.tryOnRoutes() {
    composable<TryOn> { SharedScope { TryonPlaceholder("Probador") } }
}
