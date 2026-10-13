package com.estilovivo.app.feature.suitcase

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.estilovivo.app.app.Suitcase
import com.estilovivo.app.app.TripDetail
import com.estilovivo.app.app.Wishlist
import com.estilovivo.app.app.SharedScope

/** HUECO: el módulo `suitcase` registra aquí sus destinos. */
fun NavGraphBuilder.suitcaseRoutes() {
    composable<Suitcase> { SharedScope { SuitcasePlaceholder("Viajes") } }
    composable<TripDetail> { SharedScope { SuitcasePlaceholder("Viaje") } }
    composable<Wishlist> { SharedScope { SuitcasePlaceholder("Wishlist") } }
}
