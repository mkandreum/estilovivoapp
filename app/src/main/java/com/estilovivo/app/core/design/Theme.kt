package com.estilovivo.app.core.design

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

/** Formas del sistema: esquinas muy redondeadas, botones y chips en píldora. */
object EvShapes {
    val pill = CircleShape
    val card = RoundedCornerShape(26.dp)
    val cardSmall = RoundedCornerShape(20.dp)
    val hero = RoundedCornerShape(32.dp)
    val sheet = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    val field = RoundedCornerShape(18.dp)
}

/** Espaciado en dp (escala de 4). */
object EvSpace {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 20.dp
    val xxl = 24.dp
    val huge = 32.dp
    /** Margen horizontal estándar de las pantallas. */
    val screen = 20.dp
}

object EvTheme {
    val colors: EvColors
        @Composable get() = LocalEvColors.current
}

private fun EvColors.toMaterial(): ColorScheme {
    val base = if (isDark) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = ink, onPrimary = onInk,
        primaryContainer = surfaceAlt, onPrimaryContainer = ink,
        secondary = inkSoft, onSecondary = onInk,
        secondaryContainer = surfaceAlt, onSecondaryContainer = ink,
        tertiary = accent, onTertiary = onInk,
        background = bg, onBackground = ink,
        surface = surface, onSurface = ink,
        surfaceVariant = surfaceAlt, onSurfaceVariant = inkSoft,
        surfaceContainer = surface, surfaceContainerLow = surface, surfaceContainerHigh = surface,
        surfaceContainerHighest = surfaceAlt, surfaceContainerLowest = bg,
        outline = outline, outlineVariant = outline,
        error = danger, onError = onInk,
        scrim = scrim,
    )
}

/**
 * Tema raíz. Los componentes de Material heredan la paleta editorial; el movimiento propio
 * (muelles, entradas, pulsaciones) está en [EvMotion].
 */
@Composable
fun EvTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkEvColors else LightEvColors
    CompositionLocalProvider(LocalEvColors provides colors) {
        MaterialTheme(
            colorScheme = colors.toMaterial(),
            typography = EvTypography,
            shapes = Shapes(
                extraSmall = RoundedCornerShape(10.dp),
                small = RoundedCornerShape(14.dp),
                medium = RoundedCornerShape(20.dp),
                large = RoundedCornerShape(26.dp),
                extraLarge = RoundedCornerShape(32.dp),
            ),
            content = content,
        )
    }
}
