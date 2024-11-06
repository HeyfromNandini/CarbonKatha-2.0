package project.app.carbonkatha20.presentation.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.app.carbonkatha20.R
import project.app.carbonkatha20.ui.theme.AppColors
import coil.compose.AsyncImage

@Composable
fun GroceryListScreen(onSaveList: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var groceryItems by remember { mutableStateOf(initialGroceryItems) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "List for Sunday dinner",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp),
            color = AppColors.TextPrimary
        )

        // Search Bar
        DinnerSearchField(
            searchQuery = searchQuery,
            onSearchChange = { searchQuery = it }
        )

        // Grocery List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            val filteredItems = groceryItems.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
            
            items(filteredItems) { item ->
                DinnerItemRow(
                    item = item,
                    onQuantityChanged = { newQuantity ->
                        groceryItems = groceryItems.map {
                            if (it.id == item.id) it.copy(quantity = newQuantity)
                            else it
                        }
                    }
                )
            }
        }

        // Save Button
        Button(
            onClick = {
                // Here you could save the items to a database or shared preferences
                // For now, we'll just call the callback
                onSaveList()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColors.SaveButtonColor
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "Save list & continue",
                color = AppColors.CardBackground,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun DinnerSearchField(
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        placeholder = { Text("Search items...", color = AppColors.TextSecondary) },
        leadingIcon = { Icon(Icons.Default.Search, "Search", tint = AppColors.TextSecondary) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = AppColors.CardBackground.copy(alpha = 0.5f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
private fun DinnerItemRow(
    item: DinnerItem,
    onQuantityChanged: (Float) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.CardBackground, RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item Image
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Item Name
            Text(
                text = item.name,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Medium,
                color = AppColors.TextPrimary
            )
            
            // Quantity Controls
            DinnerQuantityControls(
                quantity = item.quantity,
                unit = item.unit,
                onQuantityChanged = onQuantityChanged
            )
        }
        
        // CO2 Emission Text
        Text(
            text = "3.0 CO2 emitted for 1.9 litre",
            color = AppColors.TextSecondary,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 8.dp)
        )
    }
}

@Composable
private fun DinnerQuantityControls(
    quantity: Float,
    unit: String,
    onQuantityChanged: (Float) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Decrease Button
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(AppColors.QuantityControlBackground, CircleShape)
                .clickable { onQuantityChanged(quantity - 0.5f) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Decrease",
                tint = AppColors.QuantityControlIcon,
                modifier = Modifier.size(12.dp)
            )
        }
        
        // Quantity Text
        Text(
            text = "$quantity $unit",
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        
        // Increase Button
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(AppColors.QuantityControlBackground, CircleShape)
                .clickable { onQuantityChanged(quantity + 0.5f) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                tint = AppColors.QuantityControlIcon,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

data class DinnerItem(
    val id: Int,
    val name: String,
    val image: String,
    val quantity: Float,
    val unit: String
)

private val initialGroceryItems = listOf(
    DinnerItem(1, "Broccoli", "https://images.pexels.com/photos/47347/broccoli-vegetable-food-healthy-47347.jpeg", 0.5f, "Kg"),
    DinnerItem(2, "Potato", "https://images.pexels.com/photos/2286776/potatoes-vegetables-raw-food-2286776.jpeg", 1f, "Kg"),
    DinnerItem(3, "Brown rice", "https://images.pexels.com/photos/4110251/brown-rice-grain-food-4110251.jpeg", 5f, "Kg"),
    DinnerItem(4, "Berries", "https://images.pexels.com/photos/1379636/mixed-berries-fruit-fresh-1379636.jpeg", 1f, "Kg")
)

