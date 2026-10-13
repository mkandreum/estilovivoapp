package com.estilovivo.app.feature.social

import com.estilovivo.app.R
import com.estilovivo.app.core.design.components.ProductCardUi

/** Datos de muestra del diseño. El agente `social-shop-chat` los elimina al usar datos reales. */
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
