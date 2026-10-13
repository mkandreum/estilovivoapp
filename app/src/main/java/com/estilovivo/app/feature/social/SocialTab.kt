package com.estilovivo.app.feature.social

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import com.estilovivo.app.R
import com.estilovivo.app.app.LocalNavigator
import com.estilovivo.app.app.ProductDetail
import com.estilovivo.app.core.design.components.ProductCardUi

/** Datos de muestra para ver el diseño. El módulo `social` los sustituye por datos reales. */
internal object ShopSamples {
    val categories = listOf("Todo", "Tops", "Pantalones", "Vestidos", "Abrigos", "Zapatos", "Accesorios")
    val items = listOf(
        ProductCardUi("s1", "Crop top negro", "Tops · Talla S", "45 €", R.drawable.fashion_hero, 4.5f, aspect = 0.78f),
        ProductCardUi("s2", "Jersey Luxe", "Tops · Talla M", "38 €", R.drawable.fashion_2, 4.8f, aspect = 0.95f),
        ProductCardUi("s3", "Chaqueta cuero", "Abrigos · Talla M", "89 €", R.drawable.fashion_4, 4.6f, aspect = 0.95f),
        ProductCardUi("s4", "Conjunto urbano", "Conjuntos · Talla S", "62 €", R.drawable.fashion_3, 4.2f, aspect = 0.78f),
        ProductCardUi("s5", "Top asimétrico", "Tops · Talla L", "29 €", R.drawable.fashion_2, 4.4f, aspect = 0.82f),
        ProductCardUi("s6", "Blazer oversize", "Abrigos · Talla M", "74 €", R.drawable.fashion_hero, 4.9f, aspect = 0.9f),
    )
}

@Composable
fun SocialTab() {
    val nav = LocalNavigator.current
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(ShopSamples.categories.first()) }
    val liked = remember { mutableStateListOf<String>() }

    val shown = ShopSamples.items
        .filter { query.isBlank() || it.title.contains(query, ignoreCase = true) }
        .map { it.copy(liked = it.id in liked) }

    ShopScreen(
        greetingName = "Alexa",
        avatarInitial = "A",
        query = query, onQueryChange = { query = it },
        categories = ShopSamples.categories, selectedCategory = category, onCategory = { category = it },
        items = shown,
        onItemClick = { nav.navigate(ProductDetail(it.id)) },
        onLike = { if (it.id in liked) liked.remove(it.id) else liked.add(it.id) },
        onFilterClick = {},
    )
}
