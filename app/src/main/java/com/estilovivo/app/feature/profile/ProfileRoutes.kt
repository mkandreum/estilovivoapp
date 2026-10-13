package com.estilovivo.app.feature.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.estilovivo.app.app.Premium
import com.estilovivo.app.app.Privacy
import com.estilovivo.app.app.SharedScope

/** HUECO: el módulo `profile` registra aquí sus destinos. */
fun NavGraphBuilder.profileRoutes() {
    composable<Premium> { SharedScope { ProfilePlaceholder("Premium") } }
    composable<Privacy> { SharedScope { ProfilePlaceholder("Privacidad") } }
}
