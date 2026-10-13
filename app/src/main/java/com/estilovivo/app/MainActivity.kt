package com.estilovivo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.estilovivo.app.app.EvApp
import com.estilovivo.app.app.Welcome

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO(data): destino inicial según sesión y modo oscuro según ajustes del usuario
            EvApp(startDestination = Welcome, darkTheme = isSystemInDarkTheme())
        }
    }
}
