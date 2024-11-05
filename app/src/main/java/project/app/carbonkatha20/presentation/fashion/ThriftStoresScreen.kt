package project.app.carbonkatha20.presentation.fashion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import project.app.carbonkatha20.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThriftStoresScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        TopAppBar(
            title = { Text("Eco-Friendly Stores") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = AppColors.Background,
                titleContentColor = AppColors.TextPrimary
            )
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(thriftStores) { store ->
                ThriftStoreCard(store)
            }
        }
    }
}

@Composable
private fun ThriftStoreCard(store: ThriftStore) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            AsyncImage(
                model = store.imageUrl,
                contentDescription = store.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = store.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = AppColors.TextPrimary
                    )
                    
                    EcoScoreBadge(score = store.ecoScore)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = AppColors.TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = store.location,
                        style = MaterialTheme.typography.bodyMedium,
                        color = AppColors.TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = store.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun EcoScoreBadge(score: Float) {
    Surface(
        color = AppColors.PrimaryGreen.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Eco,
                contentDescription = null,
                tint = AppColors.PrimaryGreen,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Eco Score: $score",
                color = AppColors.PrimaryGreen,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

data class ThriftStore(
    val id: String,
    val name: String,
    val location: String,
    val description: String,
    val imageUrl: String,
    val ecoScore: Float
)

private val thriftStores = listOf(
    ThriftStore(
        "1",
        "EcoChic Boutique",
        "123 Green Street, Eco City",
        "Curated selection of vintage and sustainable fashion pieces",
        "https://example.com/store1.jpg",
        4.5f
    ),
    ThriftStore(
        "2",
        "Sustainable Threads",
        "456 Earth Avenue, Nature Town",
        "Premium second-hand clothing with focus on designer pieces",
        "https://example.com/store2.jpg",
        4.8f
    ),
    ThriftStore(
        "3",
        "Green Wardrobe",
        "789 Recycling Road, Eco Valley",
        "Affordable pre-loved fashion for the conscious consumer",
        "https://example.com/store3.jpg",
        4.2f
    )
) 