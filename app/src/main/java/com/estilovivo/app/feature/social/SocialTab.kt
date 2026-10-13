package com.estilovivo.app.feature.social

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.estilovivo.app.core.design.EvSpace
import com.estilovivo.app.core.design.components.EvSegmented
import com.estilovivo.app.feature.social.chat.ChatTab
import com.estilovivo.app.feature.social.feed.FeedTab
import com.estilovivo.app.feature.social.feed.TrendsTab
import com.estilovivo.app.feature.social.shop.ShopTab

enum class SocialSection(val label: String) { Feed("Feed"), Shop("Tienda"), Trends("Tendencias"), Chat("Chat") }

/**
 * Pestaña Social: selector de secciones. HUECO: lo posee el agente `social-feed`
 * (puede rediseñar la cabecera/selector, pero debe seguir llamando a las 4 secciones).
 */
@Composable
fun SocialTab() {
    var section by rememberSaveable { mutableStateOf(SocialSection.Feed) }
    Column(Modifier.fillMaxSize()) {
        EvSegmented(
            options = SocialSection.entries, selected = section, onSelect = { section = it }, label = { it.label },
            modifier = Modifier.statusBarsPadding().padding(start = EvSpace.screen, end = 72.dp, top = 12.dp, bottom = 8.dp).fillMaxWidth(),
        )
        AnimatedContent(
            targetState = section,
            transitionSpec = { fadeIn(tween(220)) togetherWith fadeOut(tween(120)) },
            label = "socialSection",
            modifier = Modifier.fillMaxSize(),
        ) { s ->
            Box(Modifier.fillMaxSize()) {
                when (s) {
                    SocialSection.Feed -> FeedTab()
                    SocialSection.Shop -> ShopTab()
                    SocialSection.Trends -> TrendsTab()
                    SocialSection.Chat -> ChatTab()
                }
            }
        }
    }
}
