package com.tanigo.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tanigo.app.ui.theme.Dimens
import com.tanigo.app.ui.theme.Shapes
import com.tanigo.app.ui.theme.YellowAccent

@Composable
fun ProductCard(product: com.tanigo.app.data.model.Product, navController: NavController) {
    Column(
        modifier = Modifier
//            .padding(Dimens.spacingExtraSmall)
//            .clip(Shapes.large)
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                // nanti bisa navigate ke detail
                // navController.navigate("detail/${product.name}")
            }
            .padding(Dimens.spacingSmall)
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(Dimens.spacingSmall))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(product.name, fontSize = 14.sp)
        Text(product.price.toString(), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.tertiary)

        Row(){
            Icon(
                painter = painterResource(id = com.tanigo.app.R.drawable.star),
                contentDescription = "Rating",
                tint = YellowAccent,
                modifier = Modifier.size(12.dp)
                    .align(androidx.compose.ui.Alignment.CenterVertically)
            )
            Text("${product.rating} • ${product.soldCount} sold", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        }

    }
}