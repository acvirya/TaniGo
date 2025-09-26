package com.tanigo.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tanigo.app.R
import com.tanigo.app.ui.theme.Dimens
import com.tanigo.app.ui.theme.Shapes
import com.tanigo.app.ui.theme.Surface
import com.tanigo.app.ui.theme.TaniGoTheme

@Composable
fun SuccessDialog(
    message: String,
    buttonLabel: String = "Continue",
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    // Membuat ukuran bayangan sama dengan ukuran konten
                    .matchParentSize()
                    // Geser bayangan ke bawah
                    .offset(x = 2.dp, y = 4.dp)
                    // Beri warna hitam transparan dan bentuk yang sama
                    .background(
                        color = Color.Black.copy(alpha = 0.08f),
                        shape = Shapes.extraLarge
                    )
            )
            Column(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 560.dp)
                    .clip(Shapes.extraLarge)
                    .background(Color.White)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingLarge)
            ) {
                Image(painter = painterResource(
                    id = R.drawable.circle_check),
                    contentDescription = "Success",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .size(100.dp)
                )

                Text(
                    text = "Success",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(message, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.buttonHeightMedium),
                    shape = Shapes.medium
                ) {
                    Text(text = buttonLabel,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SuccessDialogPreview() {
    TaniGoTheme {
        Surface{
            SuccessDialog(
                message = "Registration Success, please login with your new account",
                buttonLabel = "Continue",
                onDismiss = { }
            )
        }
    }


}