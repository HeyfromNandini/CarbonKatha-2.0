package project.app.carbonkatha20.presentation.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import project.app.carbonkatha20.R

@Composable
fun GroceryHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Search Bar
        SearchBar()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Offer Banner
        OfferBanner()
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Categories Header
        Text(
            text = "All categories",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Categories Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryItem(category)
            }
        }
    }
}

@Composable
private fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Search for \"Grocery\"",
            color = Color.Gray,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /* TODO */ }
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart"
            )
        }
    }
}

@Composable
private fun OfferBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFC75D3F))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Get 10% off groceries with Plus+ T&C Apply",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Spend \$30.00 to get 5%",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
private fun CategoryItem(category: Category) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(10.dp)
    ) {


        Column(modifier = Modifier.fillMaxSize(0.7f)) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = category.subtitle,
                style = MaterialTheme.typography.caption,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = category.icon),
            contentDescription = category.name,
            modifier = Modifier.size(48.dp)
        )

    }
}

data class Category(
    val name: String,
    val subtitle: String,
    val icon: Int
)

//private val categories = listOf(
//    Category("Meets", "Frozen Meal", R.drawable.ic_meat),
//    Category("Vegetable", "Markets", R.drawable.ic_vegetable),
//    Category("Fruits", "Comical tree", R.drawable.ic_fruits),
//    Category("Breads", "Burnt", R.drawable.ic_bread),
//    Category("Snacks", "Evening", R.drawable.ic_snacks),
//    Category("Bakery", "Meal and Flour", R.drawable.ic_bakery),
//    Category("Dairy & Sweet", "In store", R.drawable.ic_dairy),
//    Category("Chicken", "Frozen store", R.drawable.ic_chicken)
//)


private val categories = listOf(
    Category("Meets", "Frozen Meal", R.drawable.ic_launcher_background),
    Category("Vegetable", "Markets", R.drawable.ic_launcher_background),
    Category("Fruits", "Comical tree", R.drawable.ic_launcher_background),
    Category("Breads", "Burnt", R.drawable.ic_launcher_background),
    Category("Snacks", "Evening", R.drawable.ic_launcher_background),
    Category("Bakery", "Meal and Flour", R.drawable.ic_launcher_background),
    Category("Dairy & Sweet", "In store", R.drawable.ic_launcher_background),
    Category("Chicken", "Frozen store", R.drawable.ic_launcher_background)
)