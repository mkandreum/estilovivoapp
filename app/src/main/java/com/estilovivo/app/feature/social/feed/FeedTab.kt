package com.estilovivo.app.feature.social.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Construction
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.estilovivo.app.core.design.components.EvEmptyState

/** HUECO: agente social-feed. */
@Composable
fun FeedTab() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        EvEmptyState(Icons.Rounded.Construction, "Feed", "Pantalla en construcción.")
    }
}
