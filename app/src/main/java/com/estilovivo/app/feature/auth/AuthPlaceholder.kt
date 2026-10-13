package com.estilovivo.app.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.estilovivo.app.app.LocalNavigator
import com.estilovivo.app.core.data.fold
import com.estilovivo.app.core.data.repo.AuthRepository
import com.estilovivo.app.core.design.EvSpace
import com.estilovivo.app.core.design.components.EvHeadline
import com.estilovivo.app.core.design.components.EvPillButton
import com.estilovivo.app.core.design.components.EvTextField
import com.estilovivo.app.core.design.components.LocalEvToast
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

/**
 * ACCESO PROVISIONAL (sólo para poder probar la app mientras el módulo `auth` define el real).
 * Lo sustituye por completo el agente `auth`.
 */
@Composable
internal fun AuthPlaceholder(title: String) {
    val nav = LocalNavigator.current
    val toast = LocalEvToast.current
    val auth: AuthRepository = koinInject()
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().statusBarsPadding().imePadding().verticalScroll(rememberScrollState())
            .padding(horizontal = EvSpace.screen, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, androidx.compose.ui.Alignment.CenterVertically),
    ) {
        EvHeadline("Inicia *sesión*\n(acceso provisional)")
        EvTextField(email, { email = it }, "Email", modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
        EvTextField(password, { password = it }, "Contraseña", modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
        EvPillButton(
            text = "Entrar", loading = loading, modifier = Modifier.fillMaxWidth(),
            enabled = email.isNotBlank() && password.length >= 1,
            onClick = {
                loading = true
                scope.launch {
                    auth.login(email, password, true).fold(
                        onSuccess = { nav.enterMain() },
                        onFailure = { toast.error(it.message) },
                    )
                    loading = false
                }
            },
        )
    }
}
