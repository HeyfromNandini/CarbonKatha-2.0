import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.items
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
import coil.compose.AsyncImage
import project.app.carbonkatha20.R
import project.app.carbonkatha20.presentation.food.GroceryItem
import project.app.carbonkatha20.ui.theme.AppColors

@Composable
fun GroceryAlternativesScreen(
    onAlternativeSelected: (GroceryItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(16.dp)
    ) {
        Text(
            text = "Eco-friendly Alternatives",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(alternativeItems) { item ->
                AlternativeItemCard(
                    item = item,
                    onClick = { onAlternativeSelected(item) }
                )
            }
        }
    }
}

@Composable
private fun AlternativeItemCard(
    item: GroceryItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        backgroundColor = AppColors.CardBackground,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
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
}

private val alternativeItems = listOf(
    GroceryItem("Organic Broccoli", "1 Kg", "https://images.pexels.com/photos/47347/broccoli-vegetable-food-healthy-47347.jpeg"),
    GroceryItem("Local Cauliflower", "1 Piece", "https://images.pexels.com/photos/6316515/cauliflower-vegetable-fresh-organic-6316515.jpeg"),
    GroceryItem("Seasonal Carrots", "1 Kg", "https://images.pexels.com/photos/1306559/carrots-vegetables-fresh-raw-1306559.jpeg")
) 