package project.app.carbonkatha20.presentation.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import project.app.carbonkatha20.presentation.profile.components.AchievementCard
import project.app.carbonkatha20.presentation.profile.components.ActivityItem
import project.app.carbonkatha20.presentation.profile.components.CommunityPosts
import project.app.carbonkatha20.presentation.profile.components.ImpactChart
import project.app.carbonkatha20.presentation.profile.components.ImpactStatistics
import project.app.carbonkatha20.presentation.profile.components.achievements
import project.app.carbonkatha20.ui.theme.AppColors

@Composable
fun ProfileScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        // Top Bar with Time and Icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "9:41",
                color = AppColors.TextPrimary,
                fontSize = 16.sp
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(Icons.Default.GridView, "Menu", tint = AppColors.TextPrimary)
                Icon(Icons.Default.Download, "Download", tint = AppColors.TextPrimary)
                Icon(Icons.Default.Edit, "Edit", tint = AppColors.TextPrimary)
                Icon(Icons.Default.Settings, "Settings", tint = AppColors.TextPrimary)
            }
        }

        // Use LazyColumn instead of Column with verticalScroll
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Profile Header
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        AsyncImage(
                            model = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                        Icon(
                            Icons.Default.Eco,
                            contentDescription = "Eco Status",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(AppColors.PrimaryGreen, CircleShape)
                                .padding(4.dp),
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Rahul M",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Icon(
                            Icons.Default.EmojiEvents,
                            contentDescription = "Level",
                            tint = AppColors.PrimaryGreen
                        )
                        Text(
                            text = "Eco Warrior",
                            color = AppColors.TextSecondary,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Text(
                            text = "• June 2024",
                            color = AppColors.TextSecondary,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = AppColors.TextSecondary,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Text(
                            text = "Mumbai, India",
                            color = AppColors.TextSecondary,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Text(
                        text = "On a mission to reduce my carbon footprint 🌱 Making sustainable choices one day at a time 🌍",
                        color = AppColors.TextSecondary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            // Impact Metrics
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ImpactMetric(
                        value = "32",
                        label = "Carbon\nSaved (kg)",
                        color = AppColors.PrimaryGreen
                    )
                    ImpactMetric(
                        value = "15",
                        label = "Eco\nChoices",
                        color = Color(0xFF2196F3)
                    )
                    ImpactMetric(
                        value = "8",
                        label = "Trees\nPlanted",
                        color = Color(0xFFFF9800)
                    )
                    ImpactMetric(
                        value = "24",
                        label = "Community\nImpact",
                        color = Color(0xFFE91E63)
                    )
                }
            }

            // Tabs
            item {
                ScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = AppColors.CardBackground,
                    contentColor = AppColors.TextPrimary
                ) {
                    listOf("Achievements", "Activity", "Impact", "Community").forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }

            // Tab Content
            item {
                when (selectedTab) {
                    0 -> AchievementsTab()
                    1 -> ActivityTab()
                    2 -> ImpactTab()
                    3 -> CommunityTab()
                }
            }
        }
    }
}

@Composable
private fun ImpactMetric(
    value: String,
    label: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            progress = 0.7f,
            modifier = Modifier.size(64.dp),
            color = color,
            trackColor = color.copy(alpha = 0.2f),
            strokeWidth = 4.dp
        )
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = AppColors.TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun AchievementsTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Your Badges",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Use Grid instead of LazyGrid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            achievements.chunked(3).forEach { rowItems ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEach { achievement ->
                        AchievementCard(achievement)
                    }
                }
            }
        }
    }
}

@Composable
private fun ActivityTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Recent Activity",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary
        )
        
        // Activity items
        ActivityItem(
            icon = Icons.Default.ShoppingCart,
            title = "Eco Shopping",
            description = "Bought 5 sustainable items",
            time = "2h ago",
            carbonSaved = "1.2kg CO₂"
        )
        // Add more activity items...
    }
}

@Composable
private fun ImpactTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Your Environmental Impact",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary
        )
        
        // Impact statistics and charts
        ImpactChart()
        ImpactStatistics()
    }
}

@Composable
private fun CommunityTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Community Engagement",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.TextPrimary
        )
        
        // Community posts and interactions
        CommunityPosts()
    }
} 