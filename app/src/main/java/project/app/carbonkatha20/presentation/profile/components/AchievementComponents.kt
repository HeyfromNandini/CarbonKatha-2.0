package project.app.carbonkatha20.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import project.app.carbonkatha20.ui.theme.AppColors

data class Achievement(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val isUnlocked: Boolean = false
)

val achievements = listOf(
    Achievement(
        Icons.Default.EmojiEvents,
        "First Steps",
        "Started your sustainability journey",
        true
    ),
    Achievement(
        Icons.Default.LocalFlorist,
        "Plant Parent",
        "Planted your first tree",
        true
    ),
    Achievement(
        Icons.Default.ShoppingCart,
        "Eco Shopper",
        "Made 10 sustainable purchases",
        true
    ),
    Achievement(
        Icons.Default.DirectionsBike,
        "Green Miles",
        "Chose eco-friendly transport 20 times",
        false
    ),
    Achievement(
        Icons.Default.Restaurant,
        "Veggie Victory",
        "Chose plant-based meals for a week",
        false
    ),
    Achievement(
        Icons.Default.Recycling,
        "Waste Warrior",
        "Recycled 100 items",
        true
    )
)

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = achievement.icon,
                contentDescription = achievement.title,
                modifier = Modifier.size(32.dp),
                tint = if (achievement.isUnlocked) AppColors.PrimaryGreen else AppColors.TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = achievement.title,
                style = MaterialTheme.typography.bodySmall,
                color = if (achievement.isUnlocked) AppColors.TextPrimary else AppColors.TextSecondary
            )
        }
    }
} 