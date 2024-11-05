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

@Composable
fun GroceryListScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var groceryItems by remember { mutableStateOf(initialGroceryItems) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBF5))
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "List for Sunday dinner",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
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
        DinnerSaveButton()
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
        placeholder = { Text("Search items...") },
        leadingIcon = { Icon(Icons.Default.Search, "Search") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
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
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item Image
            Image(
                painter = painterResource(id = item.image),
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
                fontWeight = FontWeight.Medium
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
            color = Color.Gray,
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
                .background(Color(0xFFFFE0D6), CircleShape)
                .clickable { onQuantityChanged(quantity - 0.5f) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Decrease",
                tint = Color(0xFFFF5722),
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
                .background(Color(0xFFFFE0D6), CircleShape)
                .clickable { onQuantityChanged(quantity + 0.5f) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                tint = Color(0xFFFF5722),
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Composable
private fun DinnerSaveButton() {
    Button(
        onClick = { /* Handle save */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF6200EE)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            "Save list & continue",
            color = Color.White,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

data class DinnerItem(
    val id: Int,
    val name: String,
    val image: Int,
    val quantity: Float,
    val unit: String
)

private val initialGroceryItems = listOf(
    DinnerItem(1, "Broccoli", R.drawable.ic_launcher_background, 0.5f, "Kg"),
    DinnerItem(2, "Potato", R.drawable.ic_launcher_background, 1f, "Kg"),
    DinnerItem(3, "Brown rice", R.drawable.ic_launcher_background, 5f, "Kg"),
    DinnerItem(4, "Berries", R.drawable.ic_launcher_background, 1f, "Kg")
)

