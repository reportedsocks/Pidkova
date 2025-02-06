package com.antsyferov.cart.overview.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.antsyferov.cart.models.CartItemUi
import com.antsyferov.cart.overview.redux.OverviewEvents
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.theme.Green40
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun CartItem(
    cartItem: CartItemUi,
    onEvent: (OverviewEvents) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PidkovaTheme.colors.border)
            .padding(PidkovaTheme.dimensions.border)
            .clickable { onEvent(OverviewEvents.ViewProductClicked(cartItem.productId)) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = PidkovaTheme.colors.surface,
                    shape = PidkovaTheme.shapes.cardShape
                )
                .clip(PidkovaTheme.shapes.cardShape)
        ) {

            val painter = rememberAsyncImagePainter("file:///android_asset/${cartItem.productId % 10}.webp")
            Image(
                painter = painter,
                contentDescription = cartItem.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .background(Green40)
            )
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cartItem.name,
                    style = PidkovaTheme.typography.body,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = PidkovaTheme.dimensions.paddingSmall)
                )
                Text(
                    text = (cartItem.price * cartItem.quantity).toString(),
                    style = PidkovaTheme.typography.caption,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = PidkovaTheme.dimensions.paddingSmall)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        onEvent(OverviewEvents.RemoveProductClicked(cartItem.productId))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "decrement quantity"
                    )
                }
                Text(
                    text = cartItem.quantity.toString(),
                    style = PidkovaTheme.typography.caption,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = PidkovaTheme.dimensions.paddingSmall)
                )
                IconButton(
                    onClick = {
                        onEvent(OverviewEvents.AddProductClicked(cartItem.productId))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "increment quantity"
                    )
                }
            }

        }
    }
}

@Composable
@Preview
fun CartItemPreview() {
    PidkovaTheme {
        CartItem(
            cartItem = CartItemUi(
                productId = 1,
                name = "Phone",
                price = 15.3f,
                quantity = 3
            ),
            onEvent = {}
        )
    }
}