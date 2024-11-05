package project.app.carbonkatha20.presentation.food


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import coil.compose.rememberAsyncImagePainter
import project.app.carbonkatha20.R

@Composable
fun EcoProductScreen() {
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        // Header with Logo and Cart
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    "🌱",
                    fontSize = 24.sp
                )
                Text(
                    "EcoGrocer",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2E7D32)  // Dark green color
                )
            }
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint = Color.DarkGray,
                modifier = Modifier.size(24.dp)
            )
        }

        // Filter Section
        Text(
            "Filter by Category",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        // Filter Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categories.forEach { category ->
                EcoFilterChip(
                    category = category,
                    isSelected = selectedCategories.contains(category),
                    onToggle = {
                        selectedCategories = if (it) {
                            selectedCategories + category
                        } else {
                            selectedCategories - category
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Products List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val filteredProducts = if (selectedCategories.isEmpty()) {
                ecoProducts
            } else {
                ecoProducts.filter { product ->
                    product.categories.any { it in selectedCategories }
                }
            }

            items(filteredProducts) { product ->
                EcoProductCard(product)
            }
        }
    }
}

@Composable
private fun EcoFilterChip(
    category: String,
    isSelected: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(32.dp)
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = onToggle,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF2E7D32),
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White
            ),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = category,
            fontSize = 14.sp,
            color = if (isSelected) Color(0xFF2E7D32) else Color.Gray
        )
    }
}

@Composable
private fun EcoProductCard(product: EcoProduct) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f) // Give weight to this row to allow proper spacing
            ) {
                // Product Image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    error = rememberAsyncImagePainter(model = R.drawable.ic_launcher_background)
                )

                // Product Details - with constrained width
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f) // Give weight to allow text wrapping
                ) {
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = 1
                    )
                    Text(
                        text = product.description,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )
                    Text(
                        text = "Carbon Footprint: ${product.carbonFootprint}",
                        fontSize = 14.sp,
                        color = Color(0xFF2E7D32),
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp)) // Add space before button

            // Add Button
            Button(
                onClick = { /* Handle add */ },
                modifier = Modifier
                    .width(72.dp)
                    .height(32.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF76FF03),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                Text(
                    "Add",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

private val categories = listOf(
    "Organic",
    "Vegan",
    "Locally Sourced"
)

data class EcoProduct(
    val name: String,
    val description: String,
    val carbonFootprint: String,
    val imageUrl: String,
    val categories: List<String>
)

private val ecoProducts = listOf(
    EcoProduct(
        name = "Biodegradable Shampoo",
        description = "Reduces plastic waste",
        carbonFootprint = "-15%",
        imageUrl = "https://m.media-amazon.com/images/I/71R8OzZcQIL.jpg",
        categories = listOf("Organic", "Vegan")
    ),
    EcoProduct(
        name = "Beeswax Food Wrap",
        description = "Sustainable storage solution",
        carbonFootprint = "-20%",
        imageUrl = "https://m.media-amazon.com/images/I/81K7OAepyWL.jpg",
        categories = listOf("Locally Sourced")
    ),
    EcoProduct(
        name = "Glass Water Bottle",
        description = "Reusable with bamboo lid",
        carbonFootprint = "-30%",
        imageUrl = "https://m.media-amazon.com/images/I/61K3G5xadpL.jpg",
        categories = listOf("Organic", "Locally Sourced")
    )
)