package project.app.carbonkatha20.presentation.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import project.app.carbonkatha20.R
import project.app.carbonkatha20.ui.theme.AppColors

@Composable
fun GroceryCategories() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Veggies") }
    var isSearchVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        // Top Bar
        if (isSearchVisible) {
            GrocerySearchBar(
                searchQuery = searchQuery,
                onSearchChange = { searchQuery = it },
                onCloseSearch = {
                    isSearchVisible = false
                    searchQuery = ""
                }
            )
        } else {
            GroceryTopBar(
                onSearchClick = { isSearchVisible = true }
            )
        }

        // Categories
        GroceryCategoryRow(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        // Vegetables Grid
        GroceryItemsGrid(
            searchQuery = searchQuery,
            selectedCategory = selectedCategory
        )
    }
}

@Composable
private fun GrocerySearchBar(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onCloseSearch: () -> Unit
) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("Search vegetables...") },
        leadingIcon = {
            Icon(Icons.Default.Search, "Search")
        },
        trailingIcon = {
            IconButton(onClick = onCloseSearch) {
                Icon(Icons.Default.Close, "Close")
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = AppColors.SearchBarBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}

@Composable
private fun GroceryTopBar(onSearchClick: () -> Unit) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Menu, "Menu", tint = AppColors.TextPrimary)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Make your list",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
            }
            IconButton(onClick = onSearchClick) {
                Icon(Icons.Default.Search, "Search", tint = AppColors.TextPrimary)
            }
        }
    }
}

@Composable
private fun GroceryCategoryRow(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(groceryCategories) { category ->
            GroceryCategoryChip(
                category = category,
                isSelected = category.name == selectedCategory,
                onSelected = { onCategorySelected(category.name) }
            )
        }
    }
}

@Composable
private fun GroceryCategoryChip(
    category: GroceryCategory,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Surface(
        color = if (isSelected) AppColors.PrimaryGreen else AppColors.CardBackground,
        shape = RoundedCornerShape(24.dp),
        elevation = 2.dp,
        modifier = Modifier.clickable(onClick = onSelected)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = category.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = category.name,
                color = if (isSelected) AppColors.CardBackground else AppColors.TextPrimary
            )
        }
    }
}

@Composable
private fun GroceryItemsGrid(
    searchQuery: String,
    selectedCategory: String
) {
    val filteredItems = groceryItems.filter { item ->
        item.name.contains(searchQuery, ignoreCase = true) &&
        when (selectedCategory) {
            "Veggies" -> true
            else -> false // For demo, only showing veggies
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filteredItems) { item ->
            GroceryItemCard(item)
        }
    }
}

@Composable
private fun GroceryItemCard(item: GroceryItem) {
    Column(
        modifier = Modifier
            .background(AppColors.CardBackground, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.name,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Medium,
            color = AppColors.TextPrimary
        )
        Text(
            text = item.quantity,
            style = MaterialTheme.typography.caption,
            color = AppColors.TextSecondary
        )
    }
}

data class GroceryCategory(
    val name: String,
    val icon: String
)

data class GroceryItem(
    val name: String,
    val quantity: String,
    val image: String
)

private val groceryCategories = listOf(
    GroceryCategory("Veggies", "https://cdn-icons-png.flaticon.com/512/2153/2153788.png"),
    GroceryCategory("Fruits", "https://cdn-icons-png.flaticon.com/512/3194/3194766.png"),
    GroceryCategory("Meat", "https://cdn-icons-png.flaticon.com/512/3082/3082041.png"),
    GroceryCategory("Fish", "https://cdn-icons-png.flaticon.com/512/3075/3075977.png")
)

private val groceryItems = listOf(
    GroceryItem("Asparagus", "500 Gm", "https://images.pexels.com/photos/539431/asparagus-green-food-healthy-539431.jpeg"),
    GroceryItem("Broccoli", "1 Kg", "https://images.pexels.com/photos/47347/broccoli-vegetable-food-healthy-47347.jpeg"),
    GroceryItem("Brussels sprouts", "500 Gm", "https://images.pexels.com/photos/161514/brussels-sprouts-vegetables-food-161514.jpeg"),
    GroceryItem("Cabbage", "1 Piece", "https://images.pexels.com/photos/2518893/pexels-photo-2518893.jpeg"),
    GroceryItem("Carrot", "1 Kg", "https://images.pexels.com/photos/1306559/carrots-vegetables-fresh-raw-1306559.jpeg"),
    GroceryItem("Cauliflower", "1 Piece", "https://images.pexels.com/photos/6316515/cauliflower-vegetable-fresh-organic-6316515.jpeg"),
    GroceryItem("Celery", "500 Gm", "https://images.pexels.com/photos/2893635/celery-vegetable-food-fresh-2893635.jpeg"),
    GroceryItem("Corn", "1 Kg", "https://images.pexels.com/photos/547263/corn-maize-agriculture-547263.jpeg"),
    GroceryItem("Eggplant", "1 Kg", "https://images.pexels.com/photos/321551/eggplant-purple-food-321551.jpeg")
)
