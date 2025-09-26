package com.tanigo.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tanigo.app.R

@Composable
fun BrandHeader(){
    Row(){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo TaniGo",
            modifier = Modifier.size(80.dp), // atur ukuran
            contentScale = ContentScale.Fit,   // atur scaling (Crop, Fit, FillBounds)
            alignment = Alignment.Center

        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary)) {
                    append("Tani")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                    append("Go")
                }
            },
            modifier = Modifier.align(Alignment.Bottom),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

    }
}