package com.estilovivo.app.feature.planner

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.estilovivo.app.app.Planner
import com.estilovivo.app.app.SharedScope

/** HUECO: el módulo `planner` registra aquí sus destinos. */
fun NavGraphBuilder.plannerRoutes() {
    composable<Planner> { SharedScope { PlannerPlaceholder("Planificador") } }
}
