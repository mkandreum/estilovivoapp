package com.estilovivo.app.feature.social.shop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.estilovivo.app.app.LocalNavigator
import com.estilovivo.app.app.ProductDetail
import com.estilovivo.app.feature.social.ShopSamples
import com.estilovivo.app.feature.social.ShopScreen

/** HUECO con datos de muestra: lo sustituye el agente `social-shop-chat` por datos reales. */
@Composable
fun ShopTab() {
    val nav = LocalNavigator.current
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(ShopSamples.categories.first()) }
    val liked = remember { mutableStateListOf<String>() }
    val shown = ShopSamples.items
        .filter { query.isBlank() || it.title.contains(query, ignoreCase = true) }
        .map { it.copy(liked = it.id in liked) }
    ShopScreen(
        greetingName = "Alexa", avatarInitial = "A",
        query = query, onQueryChange = { query = it },
        categories = ShopSamples.categories, selectedCategory = category, onCategory = { category = it },
        items = shown,
        onItemClick = { nav.navigate(ProductDetail(it.id)) },
        onLike = { if (it.id in liked) liked.remove(it.id) else liked.add(it.id) },
    )
}
