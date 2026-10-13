package com.estilovivo.app.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.estilovivo.app.core.design.EvMotion
import com.estilovivo.app.core.design.EvTheme
import com.estilovivo.app.core.design.LocalAnimatedVisibilityScope
import com.estilovivo.app.core.design.LocalSharedTransitionScope
import com.estilovivo.app.core.design.components.EvToastHost
import com.estilovivo.app.core.design.components.EvToastState
import com.estilovivo.app.core.design.components.LocalEvToast
import com.estilovivo.app.feature.auth.authRoutes
import com.estilovivo.app.feature.notifications.notificationsRoutes
import com.estilovivo.app.feature.planner.plannerRoutes
import com.estilovivo.app.feature.profile.profileRoutes
import com.estilovivo.app.feature.social.socialRoutes
import com.estilovivo.app.feature.suitcase.suitcaseRoutes
import com.estilovivo.app.feature.tryon.tryOnRoutes
import com.estilovivo.app.feature.wardrobe.wardrobeRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

private const val SLIDE_MS = 380

private fun AnimatedContentTransitionScope<NavBackStackEntry>.pushEnter(): EnterTransition =
    slideInHorizontally(tween(SLIDE_MS, easing = EvMotion.emphasized)) { it / 3 } + fadeIn(tween(SLIDE_MS))

private fun AnimatedContentTransitionScope<NavBackStackEntry>.pushExit(): ExitTransition =
    slideOutHorizontally(tween(SLIDE_MS, easing = EvMotion.emphasized)) { -it / 6 } + fadeOut(tween(SLIDE_MS / 2))

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnter(): EnterTransition =
    slideInHorizontally(tween(SLIDE_MS, easing = EvMotion.emphasized)) { -it / 6 } + fadeIn(tween(SLIDE_MS))

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popExit(): ExitTransition =
    slideOutHorizontally(tween(SLIDE_MS, easing = EvMotion.emphasized)) { it / 3 } + fadeOut(tween(SLIDE_MS / 2))

/** Raíz de la UI: tema, toasts, navegación y transiciones de elemento compartido. */
@Composable
fun EvApp(
    startDestination: Any = Welcome,
    darkTheme: Boolean,
    /** Emite cuando la sesión caduca y no se puede renovar: se vuelve a la bienvenida. */
    sessionExpired: Flow<Unit> = emptyFlow(),
) {
    EvTheme(darkTheme = darkTheme) {
        val nav = rememberNavController()
        val toast = remember { EvToastState() }
        var tab by rememberSaveable { mutableStateOf(MainTab.Home) }
        val navigator = remember(nav) { EvNavigator(nav) { tab = it } }

        LaunchedEffect(navigator, sessionExpired) {
            sessionExpired.collect {
                navigator.resetToWelcome()
                toast.error("Tu sesión ha caducado. Vuelve a iniciar sesión.")
            }
        }

        CompositionLocalProvider(LocalEvToast provides toast, LocalNavigator provides navigator) {
            Box(Modifier.fillMaxSize().background(EvTheme.colors.bg)) {
                // Enlaces entrantes (verificación, restablecer contraseña, perfiles)
                com.estilovivo.app.feature.auth.DeepLinkEffect(nav)
                SharedTransitionLayout {
                    CompositionLocalProvider(LocalSharedTransitionScope provides this) {
                        NavHost(
                            navController = nav,
                            startDestination = startDestination,
                            enterTransition = { pushEnter() },
                            exitTransition = { pushExit() },
                            popEnterTransition = { popEnter() },
                            popExitTransition = { popExit() },
                        ) {
                            // La bienvenida entra/sale con fundido + zoom (no desliza)
                            composable<Welcome>(
                                enterTransition = { fadeIn(tween(500)) },
                                exitTransition = { fadeOut(tween(350)) + scaleOut(tween(350), targetScale = 1.06f) },
                            ) { SharedScope { com.estilovivo.app.feature.auth.WelcomeRoute() } }

                            composable<Main>(
                                enterTransition = { fadeIn(tween(450)) + scaleIn(tween(450), initialScale = 0.96f) },
                                popEnterTransition = { fadeIn(tween(250)) },
                                exitTransition = { fadeOut(tween(200)) },
                            ) {
                                SharedScope { MainShell(tab = tab, onTabChange = { tab = it }) }
                            }

                            authRoutes()
                            wardrobeRoutes()
                            plannerRoutes()
                            suitcaseRoutes()
                            tryOnRoutes()
                            socialRoutes()
                            profileRoutes()
                            notificationsRoutes()
                        }
                    }
                }
                EvToastHost(toast)
            }
        }
    }
}

/**
 * Hace disponible el `AnimatedVisibilityScope` del destino para `sharedElementIfAvailable`.
 * Cada destino que participe en una transición compartida debe envolver su contenido en esto.
 */
@Composable
fun androidx.compose.animation.AnimatedContentScope.SharedScope(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this) { content() }
}
