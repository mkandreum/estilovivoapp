package com.estilovivo.app.feature.notifications

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import com.estilovivo.app.core.design.components.EvCircleButton

/** HUECO: la campana real mostrará el nº de no leídas y escuchará el socket. */
@Composable
fun NotificationBell(onClick: () -> Unit) {
    EvCircleButton(Icons.Rounded.Notifications, onClick, contentDescription = "Notificaciones")
}
