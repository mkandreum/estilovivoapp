package com.estilovivo.app.feature.notifications

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.estilovivo.app.app.Notifications
import com.estilovivo.app.app.SharedScope

/** HUECO: el módulo `notifications` registra aquí sus destinos. */
fun NavGraphBuilder.notificationsRoutes() {
    composable<Notifications> { SharedScope { NotificationsPlaceholder("Notificaciones") } }
}
