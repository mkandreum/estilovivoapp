package com.estilovivo.app.feature.wardrobe

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.estilovivo.app.app.GarmentDetail
import com.estilovivo.app.app.CreateLook
import com.estilovivo.app.app.Washing
import com.estilovivo.app.app.SharedScope

/** HUECO: el módulo `wardrobe` registra aquí sus destinos. */
fun NavGraphBuilder.wardrobeRoutes() {
    composable<GarmentDetail> { SharedScope { WardrobePlaceholder("Prenda") } }
    composable<CreateLook> { SharedScope { WardrobePlaceholder("Crear look") } }
    composable<Washing> { SharedScope { WardrobePlaceholder("Lavadora") } }
}
