package project.app.carbonkatha20.presentation.fashion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import project.app.carbonkatha20.ui.theme.AppColors

@Composable
fun GenerateOutfitScreen(onBackClick: () -> Unit) {
    var currentOutfit by remember { mutableStateOf(generateRandomOutfit()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = AppColors.TextPrimary
                )
            }
            Text(
                text = "Generate Outfit",
                style = MaterialTheme.typography.titleLarge,
                color = AppColors.TextPrimary
            )
            IconButton(onClick = { currentOutfit = generateRandomOutfit() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Regenerate",
                    tint = AppColors.PrimaryGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Outfit Display
        OutfitPreview(outfit = currentOutfit)

        Spacer(modifier = Modifier.height(24.dp))

        // Carbon Impact
        CarbonImpactCard(impact = calculateCarbonImpact(currentOutfit))

        Spacer(modifier = Modifier.weight(1f))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { /* Save outfit */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.PrimaryGreen
                )
            ) {
                Text("Save Outfit")
            }
            
            OutlinedButton(
                onClick = { currentOutfit = generateRandomOutfit() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AppColors.PrimaryGreen
                )
            ) {
                Text("Try Another")
            }
        }
    }
}

@Composable
private fun OutfitPreview(outfit: Outfit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(outfit.items) { item ->
            OutfitItemCard(item)
        }
    }
}

@Composable
private fun OutfitItemCard(item: ClothingItem) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        )
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CarbonImpactCard(impact: Float) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Carbon Impact",
                style = MaterialTheme.typography.titleMedium,
                color = AppColors.TextPrimary
            )
            Text(
                text = "$impact kg CO₂e",
                style = MaterialTheme.typography.bodyLarge,
                color = AppColors.PrimaryGreen
            )
            Text(
                text = "30% less than average outfit",
                style = MaterialTheme.typography.bodySmall,
                color = AppColors.TextSecondary
            )
        }
    }
}

// Data classes and helper functions
data class ClothingItem(
    val id: String,
    val name: String,
    val category: String,
    val imageUrl: String
)

data class Outfit(
    val items: List<ClothingItem>
)

private fun generateRandomOutfit(): Outfit {
    return Outfit(
        listOf(
            ClothingItem("1", "White T-Shirt", "Top", "https://example.com/tshirt.jpg"),
            ClothingItem("2", "Blue Jeans", "Bottom", "https://example.com/jeans.jpg"),
            ClothingItem("3", "Sneakers", "Footwear", "https://example.com/sneakers.jpg")
        )
    )
}

private fun calculateCarbonImpact(outfit: Outfit): Float {
    // Placeholder calculation
    return 2.5f
} 