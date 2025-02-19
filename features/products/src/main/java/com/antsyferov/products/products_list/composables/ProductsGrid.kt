package com.antsyferov.products.products_list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.antsyferov.products.models.ProductUi
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun ProductsGrid(
    products: List<ProductUi>,
    onItemClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        state = state,
        horizontalArrangement = Arrangement.spacedBy(PidkovaTheme.dimensions.spacingSmall),
        verticalArrangement = Arrangement.spacedBy(PidkovaTheme.dimensions.spacingSmall),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
    ) {
        items(items = products, key = { it.id }) { product ->
            ProductItem(
                product = product,
                onClick = onItemClick,
                modifier = Modifier.testTag("product_item")
            )
        }
    }
}