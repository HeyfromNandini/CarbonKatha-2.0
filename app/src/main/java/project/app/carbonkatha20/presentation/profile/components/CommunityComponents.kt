package project.app.carbonkatha20.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import project.app.carbonkatha20.ui.theme.AppColors

data class CommunityPost(
    val userName: String,
    val action: String,
    val impact: String,
    val likes: Int,
    val comments: Int
)

val samplePosts = listOf(
    CommunityPost(
        "Rahul M",
        "Started using a bamboo toothbrush and eco-friendly toiletries",
        "Reduced plastic waste by 0.5kg this month 🌱",
        12,
        3
    ),
    CommunityPost(
        "Rahul M",
        "Completed my first week of cycling to work!",
        "Saved 2.3kg CO₂ emissions 🚲",
        8,
        5
    ),
    CommunityPost(
        "Rahul M",
        "Switched to a plant-based diet for a week",
        "Reduced carbon footprint by 4kg CO₂ 🥗",
        15,
        7
    ),
    CommunityPost(
        "Rahul M",
        "Installed solar panels at home",
        "Generating clean energy & saved 12kg CO₂ ☀️",
        20,
        9
    ),
    CommunityPost(
        "Rahul M",
        "Started composting kitchen waste",
        "Diverted 3kg waste from landfill 🌱",
        10,
        4
    )
)

@Composable
fun CommunityPosts() {
    Column {
        samplePosts.forEach { post ->
            CommunityPostCard(post)
        }
    }
}

@Composable
private fun CommunityPostCard(post: CommunityPost) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = post.userName,
                    style = MaterialTheme.typography.titleMedium,
                    color = AppColors.TextPrimary
                )
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = AppColors.TextSecondary
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = post.action,
                style = MaterialTheme.typography.bodyLarge,
                color = AppColors.TextPrimary
            )
            
            Text(
                text = post.impact,
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.PrimaryGreen
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.ThumbUp,
                        contentDescription = "Like",
                        tint = AppColors.TextSecondary
                    )
                }
                Text(
                    text = "${post.likes}",
                    color = AppColors.TextSecondary,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Comment,
                        contentDescription = "Comment",
                        tint = AppColors.TextSecondary
                    )
                }
                Text(
                    text = "${post.comments}",
                    color = AppColors.TextSecondary,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
} 