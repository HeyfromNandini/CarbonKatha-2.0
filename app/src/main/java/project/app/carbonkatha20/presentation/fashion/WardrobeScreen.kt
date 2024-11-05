package project.app.carbonkatha20.presentation.fashion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import project.app.carbonkatha20.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WardrobeScreen(
    onBackClick: () -> Unit,
    onAddItemClick: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("All") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("My Wardrobe") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            },
            actions = {
                IconButton(onClick = onAddItemClick) {
                    Icon(Icons.Default.Add, "Add Item")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = AppColors.Background,
                titleContentColor = AppColors.TextPrimary,
                navigationIconContentColor = AppColors.TextPrimary,
                actionIconContentColor = AppColors.PrimaryGreen
            )
        )

        // Category Tabs
        ScrollableTabRow(
            selectedTabIndex = categories.indexOf(selectedCategory),
            containerColor = AppColors.Background,
            contentColor = AppColors.TextPrimary
        ) {
            categories.forEach { category ->
                Tab(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    text = { Text(category) }
                )
            }
        }

        // Wardrobe Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(getWardrobeItems(selectedCategory)) { item ->
                WardrobeItemCard(item)
            }
        }
    }
}

@Composable
private fun WardrobeItemCard(item: ClothingItem) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            
            // Item details overlay
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(AppColors.CardBackground.copy(alpha = 0.8f))
                    .padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.TextPrimary
                )
                Text(
                    text = item.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = AppColors.TextSecondary
                )
            }
        }
    }
}

private val categories = listOf(
    "All",
    "Tops",
    "Bottoms",
    "Footwear",
    "Accessories"
)

private fun getWardrobeItems(category: String): List<ClothingItem> {
    // Placeholder data
    return listOf(
        ClothingItem("1", "White T-Shirt", "Tops", "https://example.com/tshirt.jpg"),
        ClothingItem("2", "Blue Jeans", "Bottoms", "https://example.com/jeans.jpg"),
        ClothingItem("3", "Sneakers", "Footwear", "https://example.com/sneakers.jpg")
    )
} 