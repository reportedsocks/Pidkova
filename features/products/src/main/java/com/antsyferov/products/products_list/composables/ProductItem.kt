package com.antsyferov.products.products_list.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.antsyferov.products.models.ProductUi
import com.antsyferov.ui.components.Chip
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.theme.Green40
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun ProductItem(
    product: ProductUi,
    onClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .widthIn(max = 150.dp)
            .fillMaxWidth()
            .background(PidkovaTheme.colors.border)
            .padding(PidkovaTheme.dimensions.border)
            .clickable { onClick(product) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = PidkovaTheme.colors.surface, shape = PidkovaTheme.shapes.cardShape)
                .clip(PidkovaTheme.shapes.cardShape)
        ) {

            val painter = rememberAsyncImagePainter("file:///android_asset/${product.id % 10}.webp")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(20.dp)
                )
                Image(
                    painter = painter,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(60.dp)
                        .background(Green40)
                )
            }


            Text(
                text = product.name,
                style = PidkovaTheme.typography.body,
                maxLines = 1,
                modifier = Modifier.padding(horizontal = PidkovaTheme.dimensions.paddingSmall)
            )

            Text(
                text = product.brand,
                style = PidkovaTheme.typography.overline,
                maxLines = 1,
                modifier = Modifier.padding(horizontal = PidkovaTheme.dimensions.paddingSmall)
            )
            Spacer(Modifier.height(PidkovaTheme.dimensions.spacingSmall))

            Chip(
                text = product.price.toString(),
                modifier = Modifier
                    .align(Alignment.Start)
            )
        }
    }

}

@Composable
@Preview(
    widthDp = 100
)
fun ProductItemPreview() {
    PidkovaTheme {
        ProductItem(
            product = ProductUi(
                id = 1,
                name = "Product 1",
                brand = "Samsung",
                price = 13.3f,
                rating = 2.5f,
                "Lorem ipsum bla bla bla"
            ),
            onClick = {}
        )
    }
}