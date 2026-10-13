package com.estilovivo.app.core.data.session

import android.content.Context
import androidx.compose.runtime.Immutable
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

/** Modo de tema elegido por el usuario. */
enum class ThemeMode(val key: String) {
    System("system"), Light("light"), Dark("dark");

    companion object {
        fun fromKey(key: String?): ThemeMode = entries.firstOrNull { it.key == key } ?: System
    }
}

/** Foto de todas las preferencias locales. */
@Immutable
data class AppSettings(
    val themeMode: ThemeMode = ThemeMode.System,
    val rememberMe: Boolean = true,
    val onboardingDone: Boolean = false,
    /** Multiplicador del tamaño de texto (0.85..1.5). */
    val fontScale: Float = 1f,
    val highContrast: Boolean = false,
    val haptics: Boolean = true,
)

private val Context.appPrefsStore: DataStore<Preferences> by preferencesDataStore(
    name = "app_prefs",
    corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
)

/** Preferencias de la app en DataStore. Lectura reactiva con Flows, escritura con funciones `suspend`. */
class AppPrefs(context: Context) {
    private val store = context.applicationContext.appPrefsStore

    private object Keys {
        val themeMode = stringPreferencesKey("theme_mode")
        val rememberMe = booleanPreferencesKey("remember_me")
        val onboardingDone = booleanPreferencesKey("onboarding_done")
        val fontScale = floatPreferencesKey("font_scale")
        val highContrast = booleanPreferencesKey("high_contrast")
        val haptics = booleanPreferencesKey("haptics")
    }

    val settings: Flow<AppSettings> = store.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { p ->
            AppSettings(
                themeMode = ThemeMode.fromKey(p[Keys.themeMode]),
                rememberMe = p[Keys.rememberMe] ?: true,
                onboardingDone = p[Keys.onboardingDone] ?: false,
                fontScale = (p[Keys.fontScale] ?: 1f).coerceIn(0.85f, 1.5f),
                highContrast = p[Keys.highContrast] ?: false,
                haptics = p[Keys.haptics] ?: true,
            )
        }
        .distinctUntilChanged()

    val themeMode: Flow<ThemeMode> = settings.map { it.themeMode }.distinctUntilChanged()
    val rememberMe: Flow<Boolean> = settings.map { it.rememberMe }.distinctUntilChanged()
    val onboardingDone: Flow<Boolean> = settings.map { it.onboardingDone }.distinctUntilChanged()
    val fontScale: Flow<Float> = settings.map { it.fontScale }.distinctUntilChanged()
    val highContrast: Flow<Boolean> = settings.map { it.highContrast }.distinctUntilChanged()
    val haptics: Flow<Boolean> = settings.map { it.haptics }.distinctUntilChanged()

    /** Lectura puntual (útil en el arranque). */
    suspend fun current(): AppSettings = settings.first()

    suspend fun setThemeMode(mode: ThemeMode) { store.edit { it[Keys.themeMode] = mode.key } }
    suspend fun setRememberMe(value: Boolean) { store.edit { it[Keys.rememberMe] = value } }
    suspend fun setOnboardingDone(value: Boolean) { store.edit { it[Keys.onboardingDone] = value } }
    suspend fun setFontScale(value: Float) { store.edit { it[Keys.fontScale] = value.coerceIn(0.85f, 1.5f) } }
    suspend fun setHighContrast(value: Boolean) { store.edit { it[Keys.highContrast] = value } }
    suspend fun setHaptics(value: Boolean) { store.edit { it[Keys.haptics] = value } }
}
