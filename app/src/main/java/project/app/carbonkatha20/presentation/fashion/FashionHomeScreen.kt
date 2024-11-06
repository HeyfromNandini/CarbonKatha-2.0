package project.app.carbonkatha20.presentation.fashion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import project.app.carbonkatha20.ui.theme.AppColors

@Composable
fun FashionHomeScreen(
    onGenerateOutfitClick: () -> Unit,
    onWardrobeClick: () -> Unit,
    onThriftStoresClick: () -> Unit,
    onAddItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(16.dp)
    ) {
        Text(
            text = "Fashion",
            style = MaterialTheme.typography.headlineMedium,
            color = AppColors.TextPrimary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Main options grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FashionOptionCard(
                title = "Generate\nOutfit",
                icon = Icons.Default.Refresh,
                modifier = Modifier.weight(1f),
                onClick = onGenerateOutfitClick
            )
            
            FashionOptionCard(
                title = "Browse\nWardrobe",
                icon = Icons.Default.GridView,
                modifier = Modifier.weight(1f),
                onClick = onWardrobeClick
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FashionOptionCard(
                title = "Top Thrift\nStores",
                icon = Icons.Default.Store,
                modifier = Modifier.weight(1f),
                onClick = onThriftStoresClick
            )
            
            FashionOptionCard(
                title = "Add\nWardrobe",
                icon = Icons.Default.Add,
                modifier = Modifier.weight(1f),
                onClick = onAddItemClick
            )
        }

        // Recent outfits section
        Text(
            text = "Recent Outfits",
            style = MaterialTheme.typography.titleMedium,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // Placeholder for recent outfits
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(AppColors.CardBackground)
        )
    }
}

@Composable
private fun FashionOptionCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppColors.PrimaryGreen,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = AppColors.TextPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
} 