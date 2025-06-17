package com.michael.restaurant.ui.screen.menu.delicious

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.michael.restaurant.R
import com.michael.restaurant.data.database.menu.model.Category
import com.michael.restaurant.data.database.menu.model.Dish
import com.michael.restaurant.ui.component.ScreenLoader
import com.michael.restaurant.ui.component.SomethingWentWrong

@Composable
fun DeliciousSection(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    viewModel: DeliciousViewModel = hiltViewModel()
) {
    val state = viewModel.deliciousState.collectAsState()

    when (val value = state.value) {
        is DeliciousState.Loading -> ScreenLoader()
        is DeliciousState.Error -> SomethingWentWrong(modifier.fillMaxWidth())
        is DeliciousState.Success -> SuccessState(
            modifier = modifier,
            filterList = value.category,
            dishList = value.dishes,
            selectedCategoryId = value.selectedCategoryId,
            onFilterClick = viewModel::selectCategory,
            isExpanded = isExpanded
        )
    }

}

@Composable
private fun SuccessState(
    modifier: Modifier = Modifier,
    filterList: List<Category>,
    dishList: List<Dish>,
    selectedCategoryId: Int?,
    onFilterClick: (Int) -> Unit,
    isExpanded: Boolean
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.delicious_menu_title),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        FilterRow(
            items = filterList,
            selectedId = selectedCategoryId,
            onClick = onFilterClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        MenuList(isExpanded = isExpanded, dishes = dishList)
    }
}

@Composable
private fun MenuList(
    isExpanded: Boolean,
    dishes: List<Dish>
) {
    LazyVerticalGrid(
        modifier = Modifier.heightIn(max = 500.dp),
        columns = GridCells.Fixed(if (isExpanded) 4 else 2),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dishes, key = { it.id }) { dish ->
            DishCard(
                modifier = Modifier.animateItem(),
                dish = dish
            )
        }
    }
}

@Composable
private fun DishCard(
    modifier: Modifier = Modifier,
    dish: Dish
) {
    Card(
        modifier = modifier
            .padding(bottom = 8.dp)
    ) {
        AsyncImage(
            modifier = Modifier.height(140.dp),
            model = "file:///android_asset/image/menu/${dish.photoPath}",
            contentDescription = dish.name,
            placeholder = painterResource(R.drawable.placeholder),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = dish.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$${dish.price}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun FilterRow(
    items: List<Category>,
    selectedId: Int?,
    onClick: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items, key = { it.id }) { category ->
            FilterChip(
                selected = category.id == selectedId,
                onClick = { onClick(category.id) },
                label = {
                    Text(text = category.name)
                }
            )
        }
    }
}

@Preview(device = Devices.PIXEL, showSystemUi = true)
@Composable
fun DeliciousPreview() {
    Scaffold { innerPadding ->
        DeliciousSection(modifier = Modifier.padding(innerPadding))
    }
}

@Preview(device = Devices.TABLET, showSystemUi = true)
@Composable
fun DeliciousPreviewTablet() {
    Scaffold { innerPadding ->
        DeliciousSection(modifier = Modifier.padding(innerPadding), isExpanded = true)
    }
}