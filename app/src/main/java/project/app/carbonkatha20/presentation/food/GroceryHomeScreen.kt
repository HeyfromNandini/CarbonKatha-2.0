package project.app.carbonkatha20.presentation.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
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
import coil.compose.AsyncImage
import project.app.carbonkatha20.R
import project.app.carbonkatha20.ui.theme.AppColors

@Composable
fun GroceryHomeScreen(
    onSearchClick: () -> Unit,
    onCategoryClick: () -> Unit,
    onMessageClick: () -> Unit,
    selectedItems: List<GroceryItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(16.dp)
    ) {
        // Search Bar
        SearchBar(
            onSearchClick = onSearchClick,
            onMessageClick = onMessageClick)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Offer Banner
        OfferBanner()
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Categories Header
        Text(
            text = "All categories",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Categories Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryItem(category) {
                    onCategoryClick()
                }
            }
        }

        // Add a new section for eco-friendly choices
        Text(
            text = "Your Eco-friendly Choices",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(selectedItems) { item ->
                EcoFriendlyItemCard(item)
            }
        }
    }
}

@Composable
private fun SearchBar(onSearchClick: () -> Unit = {}, onMessageClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, AppColors.BorderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp)
            .clickable {
                onSearchClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = AppColors.TextSecondary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Search for \"Grocery\"",
            color = AppColors.TextSecondary,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onMessageClick
        ) {
            Icon(
                imageVector = Icons.Default.Message,
                contentDescription = "Cart",
                tint = AppColors.TextSecondary
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
            .background(AppColors.OfferBannerColor)
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
private fun CategoryItem(category: Category, onCategoryClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, AppColors.BorderColor, RoundedCornerShape(8.dp))
            .padding(10.dp)
            .clickable {
                onCategoryClick()
            }
    ) {


        Column(modifier = Modifier.fillMaxSize(0.7f)) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
                color = AppColors.TextPrimary
            )
            Text(
                text = category.subtitle,
                style = MaterialTheme.typography.caption,
                color = AppColors.TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        AsyncImage(
            model = category.icon,
            contentDescription = category.name,
            modifier = Modifier.size(48.dp)
        )

    }
}

@Composable
private fun EcoFriendlyItemCard(item: GroceryItem) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(8.dp)),
        backgroundColor = AppColors.CardBackground,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = item.name,
                style = MaterialTheme.typography.subtitle1,
                color = AppColors.TextPrimary
            )
            Text(
                text = "Carbon footprint: 2.5 CO₂e",
                style = MaterialTheme.typography.caption,
                color = AppColors.TextSecondary
            )
            Text(
                text = "30% less impact than regular option",
                style = MaterialTheme.typography.caption,
                color = AppColors.PrimaryGreen
            )
        }
    }
}

data class Category(
    val name: String,
    val subtitle: String,
    val icon: String
)

private val categories = listOf(
    Category("Meets", "Frozen Meal", "https://cdn-icons-png.flaticon.com/512/3082/3082041.png"),
    Category("Vegetable", "Markets", "https://cdn-icons-png.flaticon.com/512/2153/2153788.png"),
    Category("Fruits", "Comical tree", "https://cdn-icons-png.flaticon.com/512/3194/3194766.png"),
    Category("Breads", "Burnt", "https://cdn-icons-png.flaticon.com/512/3052/3052098.png"),
    Category("Snacks", "Evening", "https://cdn-icons-png.flaticon.com/512/2553/2553691.png"),
    Category("Bakery", "Meal and Flour", "https://cdn-icons-png.flaticon.com/512/3081/3081967.png"),
    Category("Dairy & Sweet", "In store", "https://cdn-icons-png.flaticon.com/512/3050/3050158.png"),
    Category("Chicken", "Frozen store", "https://cdn-icons-png.flaticon.com/512/1046/1046769.png")
)

private val selectedEcoFriendlyItems = listOf(
    GroceryItem("Organic Broccoli", "1 Kg", "https://images.pexels.com/photos/47347/broccoli-vegetable-food-healthy-47347.jpeg"),
    GroceryItem("Local Carrots", "1 Kg", "https://images.pexels.com/photos/1306559/carrots-vegetables-fresh-raw-1306559.jpeg")
)