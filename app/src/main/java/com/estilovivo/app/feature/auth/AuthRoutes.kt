package com.estilovivo.app.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.estilovivo.app.app.Auth
import com.estilovivo.app.app.Onboarding
import com.estilovivo.app.app.SharedScope

/** HUECO: el módulo `auth` registra aquí Auth y Onboarding (Welcome ya está en EvApp). */
fun NavGraphBuilder.authRoutes() {
    composable<Auth> { SharedScope { AuthPlaceholder("Acceso") } }
    composable<Onboarding> { SharedScope { AuthPlaceholder("Bienvenida") } }
}
