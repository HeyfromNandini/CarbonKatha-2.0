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
import project.app.carbonkatha20.R

@Composable
fun GroceryCategories() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Veggies") }
    var isSearchVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBF5))
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
            backgroundColor = Color.White,
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
                Icon(Icons.Default.Menu, "Menu")
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Make your list",
                    style = MaterialTheme.typography.h6
                )
            }
            IconButton(onClick = onSearchClick) {
                Icon(Icons.Default.Search, "Search")
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
        color = if (isSelected) Color(0xFFFF5722) else Color.White,
        shape = RoundedCornerShape(24.dp),
        elevation = 2.dp,
        modifier = Modifier.clickable(onClick = onSelected)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = category.name,
                color = if (isSelected) Color.White else Color.Black
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
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = item.image),
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
            fontWeight = FontWeight.Medium
        )
        Text(
            text = item.quantity,
            style = MaterialTheme.typography.caption,
            color = Color.Gray
        )
    }
}

data class GroceryCategory(
    val name: String,
    val icon: Int
)

data class GroceryItem(
    val name: String,
    val quantity: String,
    val image: Int
)

private val groceryCategories = listOf(
    GroceryCategory("Veggies", R.drawable.ic_launcher_background),
    GroceryCategory("Fruits", R.drawable.ic_launcher_background),
    GroceryCategory("Meat", R.drawable.ic_launcher_background),
    GroceryCategory("Fish", R.drawable.ic_launcher_background)
)


private val groceryItems = listOf(
    GroceryItem("Asparagus", "500 Gm", R.drawable.ic_launcher_background),
    GroceryItem("Broccoli", "1 Kg", R.drawable.ic_launcher_background),
    GroceryItem("Brussels sprouts", "500 Gm", R.drawable.ic_launcher_background),
    GroceryItem("Cabbage", "1 Piece", R.drawable.ic_launcher_background),
    GroceryItem("Carrot", "1 Kg", R.drawable.ic_launcher_background),
    GroceryItem("Cauliflower", "1 Piece", R.drawable.ic_launcher_background),
    GroceryItem("Celery", "500 Gm", R.drawable.ic_launcher_background),
    GroceryItem("Corn", "1 Kg", R.drawable.ic_launcher_background),
    GroceryItem("Eggplant", "1 Kg", R.drawable.ic_launcher_background)
)
