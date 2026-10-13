package com.estilovivo.app.feature.social

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.estilovivo.app.R
import com.estilovivo.app.app.LocalNavigator
import com.estilovivo.app.app.ProductDetail
import com.estilovivo.app.app.SharedScope

/** Destinos de Comunidad. HUECO parcial: el módulo `social` añade aquí chat, perfil ajeno, historias, etc. */
fun NavGraphBuilder.socialRoutes() {
    composable<ProductDetail> { entry ->
        val route = entry.toRoute<ProductDetail>()
        SharedScope { ProductDetailRoute(route.id) }
    }
}

@Composable
private fun ProductDetailRoute(id: String) {
    val nav = LocalNavigator.current
    val sample = ShopSamples.items.firstOrNull { it.id == id } ?: ShopSamples.items.first()
    var liked by remember { mutableStateOf(false) }
    ProductDetailScreen(
        item = ProductDetailUi(
            id = sample.id, title = sample.title,
            images = listOf(sample.image!!, R.drawable.fashion_2, R.drawable.fashion_3),
            price = sample.price.orEmpty(), rating = sample.rating,
            brand = "Zara", color = "Negro", condition = "Buen estado",
            sizes = listOf("XS", "S", "M", "L", "XL"), size = "S",
            description = "Prenda cuidada, usada pocas veces. Tejido suave y elástico, perfecta para looks de entretiempo.",
            sellerName = "Alexa Moreno", liked = liked,
        ),
        onBack = { nav.back() },
        onToggleLike = { liked = !liked },
        onContact = {},
        onSellerClick = {},
    )
}
