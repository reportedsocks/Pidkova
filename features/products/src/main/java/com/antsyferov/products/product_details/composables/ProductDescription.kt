package com.antsyferov.products.product_details.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.antsyferov.products.models.ProductUi
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.theme.Green40
import com.antsyferov.ui.theme.PidkovaTheme

@Composable
fun ProductDescription(
    product: ProductUi,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Green40)
    ) {
        val painter = rememberAsyncImagePainter("file:///android_asset/${product.id % 10}.webp")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(Green40),
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
                    .fillMaxHeight(0.8f)

            )
        }
        
        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingMedium))

        Text(
            text = product.name,
            style = PidkovaTheme.typography.title
        )

        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingMedium))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(PidkovaTheme.dimensions.spacingMedium),
            modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
        ) {
            Text(
                text = "Price:",
                style = PidkovaTheme.typography.caption,
            )
            Text(
                text = product.price.toString(),
                style = PidkovaTheme.typography.body,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(PidkovaTheme.dimensions.spacingMedium),
            modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
        ) {
            Text(
                text = "Brand:",
                style = PidkovaTheme.typography.caption,
            )
            Text(
                text = product.brand,
                style = PidkovaTheme.typography.body,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(PidkovaTheme.dimensions.spacingMedium),
            modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .padding(horizontal = PidkovaTheme.dimensions.paddingLarge)
        ) {
            Text(
                text = "Rating:",
                style = PidkovaTheme.typography.caption,
            )
            Text(
                text = product.rating.toString(),
                style = PidkovaTheme.typography.body,
            )
        }

        Spacer(Modifier.height(PidkovaTheme.dimensions.spacingMedium))

        Text(
            text = product.description,
            maxLines = 5,
            modifier = Modifier.padding(PidkovaTheme.dimensions.paddingLarge)
        )
    }
}

    @Composable
    @Preview(showBackground = true, backgroundColor = 0xFFF)
fun ProductDescriptionPreview() {
    PidkovaTheme {
        ProductDescription(
            ProductUi(
                1,
                "Phone",
                "Samsung",
                12.3f,
                4.3f,
                "Lorem ipsum bla bla bla"
            )
        )
    }
}