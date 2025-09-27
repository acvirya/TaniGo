package com.tanigo.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tanigo.app.R
import com.tanigo.app.ui.theme.Dimens
import com.tanigo.app.ui.theme.TaniGoTheme
import com.tanigo.app.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.hilt.navigation.compose.hiltViewModel
import com.tanigo.app.data.repository.DummyRepository
import com.tanigo.app.ui.components.ProductCard
import kotlin.collections.get
import kotlin.text.category
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val products = viewModel.products.collectAsState().value
    // Main Container
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        // Banner Container
        Column{
        // TODO: Banner
        }


        // Search bar
        val filteredProducts by viewModel.filteredProducts.collectAsState()
        val searchQuery by viewModel.searchQuery.collectAsState()
        val selectedCategory by viewModel.selectedCategory.collectAsState()
        val categories = listOf("All", "Traktor", "Pompa", "Cangkul", "Sabit")

        Row(
            modifier = Modifier.fillMaxWidth().padding(Dimens.spacingMedium),
            horizontalArrangement = Arrangement.spacedBy(Dimens.spacingSmall)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::onSearchQueryChange,
                placeholder = { Text("Cari alat pertanian") },
                modifier = Modifier.weight(0.7f).height(Dimens.heightLarge)
            )

            Button(
                onClick = { /* handle search */ },
                modifier = Modifier
                    .weight(0.3f)
                    .height(Dimens.heightLarge),
                shape = MaterialTheme.shapes.medium)
            {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary) )
            }
        }


        // Category filter row (scrollable)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = Dimens.spacingMedium)
        ) {
            items(categories) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { viewModel.onCategorySelected(category) },
                    label = { Text(category) },
                    modifier = Modifier.heightIn(min = 36.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }

        // Product container
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(filteredProducts) { product ->
                ProductCard(product = product, navController =  navController)
            }
        }
    }
}
@Composable
fun HomeTopAppBar(navController: NavController, drawerState: DrawerState){
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(Dimens.spacingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Discovery", style = MaterialTheme.typography.titleLarge)

        Image(
            painter = painterResource(id = R.drawable.blank_gray_circle),
            contentDescription = "Profile",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .clickable {
                    scope.launch{
                        drawerState.open()
                    }
                }
        )
    }
}
