package com.estilovivo.app.feature.social

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SearchOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.estilovivo.app.core.design.EvSpace
import com.estilovivo.app.core.design.EvTheme
import com.estilovivo.app.core.design.EvType
import com.estilovivo.app.core.design.components.EvChipRow
import com.estilovivo.app.core.design.components.EvEmptyState
import com.estilovivo.app.core.design.components.EvHeadline
import com.estilovivo.app.core.design.components.EvProductCard
import com.estilovivo.app.core.design.components.EvSearchBar
import com.estilovivo.app.core.design.components.ProductCardUi
import com.estilovivo.app.core.design.staggeredEntrance

/**
 * Catálogo de la comunidad (pantalla "Home" de la referencia): saludo, titular editorial con serif
 * cursiva, buscador en píldora con botón de filtros, chips de categoría y rejilla escalonada
 * de tarjetas con corazón. Sin estado propio: lo gobierna quien lo use (ver `SocialTab`).
 */
@Composable
fun ShopScreen(
    greetingName: String,
    avatarInitial: String,
    query: String,
    onQueryChange: (String) -> Unit,
    categories: List<String>,
    selectedCategory: String,
    onCategory: (String) -> Unit,
    items: List<ProductCardUi>,
    onItemClick: (ProductCardUi) -> Unit,
    onLike: (ProductCardUi) -> Unit,
    onFilterClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    bottomPadding: androidx.compose.ui.unit.Dp = 132.dp,
) {
    val c = EvTheme.colors
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = EvSpace.screen, end = EvSpace.screen, bottom = bottomPadding),
        verticalItemSpacing = 14.dp,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            Column(Modifier.statusBarsPadding().padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Row(
                    Modifier.padding(end = 56.dp).staggeredEntrance(0),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        Modifier.size(46.dp).clip(CircleShape).background(c.ink),
                        contentAlignment = Alignment.Center,
                    ) { Text(avatarInitial, style = EvType.title, color = c.onInk) }
                    Column {
                        Text("Hola,", style = EvType.caption, color = c.inkMuted)
                        Text(greetingName, style = EvType.subtitle, color = c.ink)
                    }
                }
                EvHeadline(
                    raw = "Tendencias a tu medida,\n*seleccionadas* para ti.",
                    modifier = Modifier.staggeredEntrance(1),
                )
                EvSearchBar(
                    value = query, onValueChange = onQueryChange, placeholder = "Buscar prendas, marcas…",
                    onFilterClick = onFilterClick,
                    modifier = Modifier.staggeredEntrance(2),
                )
                EvChipRow(
                    items = categories, selected = selectedCategory, onSelect = onCategory, label = { it },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.fillMaxWidth().staggeredEntrance(3),
                )
                Box(Modifier.size(2.dp))
            }
        }
        if (items.isEmpty()) {
            item(span = StaggeredGridItemSpan.FullLine) {
                EvEmptyState(
                    icon = Icons.Rounded.SearchOff,
                    title = "Nada por aquí",
                    message = "Prueba con otra búsqueda o categoría.",
                )
            }
        } else {
            itemsIndexed(items, key = { _, it -> it.id }) { index, item ->
                EvProductCard(
                    item = item,
                    onClick = { onItemClick(item) },
                    onLike = { onLike(item) },
                    index = index + 4,
                )
            }
        }
    }
}
