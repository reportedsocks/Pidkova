package com.antsyferov.cart.overview.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antsyferov.cart.models.CartItemUi
import com.antsyferov.cart.overview.redux.OverviewEvents
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun CartItemsList(
    items: List<CartItemUi>,
    onEvent: (OverviewEvents) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = PidkovaTheme.dimensions.paddingLarge),
        verticalArrangement = Arrangement.spacedBy(PidkovaTheme.dimensions.paddingMedium),
        state = state,
        modifier = modifier.padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
    ) {
        items(items, key = { it.productId }) { cartItem ->
            CartItem(
                cartItem = cartItem,
                onEvent = onEvent
            )
        }
    }
}