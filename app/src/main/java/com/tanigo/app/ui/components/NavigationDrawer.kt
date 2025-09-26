package com.tanigo.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tanigo.app.R
import com.tanigo.app.ui.theme.Dimens

@Composable
fun NavigationDrawer(onDestinationClicked: (route: String) -> Unit) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){

        // Main Container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Dimens.spacingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Container Profile
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.spacingMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingSmall)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.blank_gray_circle),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                Text(
                    text = "Mea",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = Dimens.spacingMedium),  // jarak dari elemen lain
                thickness = 1.dp,        // ketebalan garis
                color = MaterialTheme.colorScheme.outline      // warna garis
            )

            // Container Navigasi
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.spacingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingMedium)
            ) {
                Text(
                    text = "Profile",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("home") }
                )

                Text(
                    text = "Account",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("home") }
                )

                Text(
                    text = "Settings",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("settings") }
                )

                Text(
                    text = "FAQ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("settings") }
                )

                Text(
                    text = "Log Out",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClicked("settings") }
                )
            }
        }
    }
}