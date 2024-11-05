import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// [Previous imports remain the same]

object AppColors {
    val Background = Color(0xFF1A1C19) // Dark earthy black
    val CardBackground = Color(0xFF2C312C) // Slightly lighter black
    val PrimaryGreen = Color(0xFF7CB342) // Earthy green
    val SecondaryGreen = Color(0xFF558B2F) // Darker green
    val AccentGold = Color(0xFFD4B64C) // Earthy gold
    val TextPrimary = Color(0xFFE8E8E8) // Off-white
    val TextSecondary = Color(0xFFB3B3B3) // Light gray
    val ProgressBackground = Color(0xFF3A3F3A) // Dark background for progress bars

    // Macro colors
    val CarbsColor = Color(0xFFD4B64C) // Gold
    val ProteinColor = Color(0xFF7CB342) // Primary green
    val FatColor = Color(0xFF558B2F) // Dark green

    // Gradient colors (for circular progress only)
    val GradientColors = listOf(
        Color(0xFF4CAF50),  // Green
        Color(0xFFBA68C8),  // Purple
        Color(0xFFE6A57A)   // Peach
    )
}

@Composable
fun DashboardScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Top Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Carbon Katha",
                            style = MaterialTheme.typography.headlineMedium,
                            color = AppColors.TextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = AppColors.TextPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = AppColors.TextPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // Gradient Background for Stats Section
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF4CAF50).copy(alpha = 0.95f),  // Green with slight transparency
                                        Color(0xFFBA68C8).copy(alpha = 0.95f),  // Purple with slight transparency
                                        Color(0xFFE6A57A).copy(alpha = 0.95f),  // Peach with slight transparency
                                    ),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY
                                )
                            )
                    ) {
                        // Add an overlay for smoother transition
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            AppColors.Background.copy(alpha = 0.1f),
                                            AppColors.Background.copy(alpha = 0.3f)
                                        ),
                                        startY = 300f,
                                        endY = Float.POSITIVE_INFINITY
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp, vertical = 32.dp)
                        ) {
                            // Stats section
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                // Left stat
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.CenterStart),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "3143",
                                        color = Color.White,
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "EATEN",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                                // Center circle
                                Box(
                                    modifier = Modifier
                                        .size(180.dp)
                                        .border(2.dp, Color.White, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "273",
                                            style = MaterialTheme.typography.headlineLarge,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "KCAL OVER",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.White
                                        )
                                    }
                                }

                                // Right stat
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "651",
                                        color = Color.White,
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "BURNED",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            Text(
                                text = "SEE STATS",
                                color = Color.White,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Macronutrients Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MacronutrientItem(
                                label = "Carbs",
                                current = 271,
                                goal = 359,
                                color = AppColors.CarbsColor
                            )
                            MacronutrientItem(
                                label = "Protein",
                                current = 207,
                                goal = 143,
                                color = AppColors.ProteinColor
                            )
                            MacronutrientItem(
                                label = "Fat",
                                current = 133,
                                goal = 96,
                                color = AppColors.FatColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Categories Section
                    SectionTitle("Categories")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CategoryCard("Food", AppColors.CardBackground)
                        CategoryCard("Fashion", AppColors.PrimaryGreen)
                        CategoryCard("Tech", AppColors.SecondaryGreen)
                    }

                    // Date Selector
                    DateSelector(date = "TODAY, JUL 27")

                    Spacer(modifier = Modifier.height(8.dp))

                    // Day Rating Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "☺",
                                fontSize = 48.sp,
                                color = AppColors.AccentGold,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Day rating",
                                style = MaterialTheme.typography.titleLarge,
                                color = AppColors.TextPrimary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Eat more healthy carbs to boost your day rating!",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = AppColors.TextSecondary
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "SEE MORE",
                                color = AppColors.PrimaryGreen,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Additional Content
                    AdditionalContent()
                }
            }
        }
    }
}

@Composable
private fun MacronutrientItem(
    label: String,
    current: Int,
    goal: Int,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.TextPrimary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "$current/$goal${if (label == "Fat") "g" else "g"}",
            style = MaterialTheme.typography.bodySmall,
            color = AppColors.TextSecondary,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(AppColors.ProgressBackground)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp * (current.toFloat() / goal))
                    .clip(RoundedCornerShape(2.dp))
                    .background(color)
            )
        }
    }
}

@Composable
private fun DateSelector(date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous day",
                    tint = AppColors.PrimaryGreen
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Calendar",
                    tint = AppColors.PrimaryGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyLarge,
                    color = AppColors.TextPrimary,
                    fontWeight = FontWeight.Medium
                )
            }

            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next day",
                    tint = AppColors.PrimaryGreen
                )
            }
        }
    }
}

@Composable
private fun BottomNavigation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.CardBackground)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavItem("Diary", Icons.Default.Book, true)
        BottomNavItem("Progress", Icons.Default.ShowChart)
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(AppColors.PrimaryGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = AppColors.TextPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
        BottomNavItem("Diets", Icons.Default.Restaurant)
        BottomNavItem("Recipes", Icons.Default.MenuBook)
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) AppColors.PrimaryGreen else AppColors.TextSecondary,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = if (isSelected) AppColors.PrimaryGreen else AppColors.TextSecondary
        )
    }
}

@Composable
fun AdditionalContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Eco-Tips Section
        SectionTitle("Eco-Tips")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Learn how to reduce your carbon footprint with daily tips.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = AppColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.PrimaryGreen)
                ) {
                    Text("Discover More", color = AppColors.TextPrimary)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = AppColors.TextPrimary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun CategoryCard(title: String, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = when (title) {
                    "Food" -> Icons.Default.Restaurant
                    "Fashion" -> Icons.Default.Link
                    else -> Icons.Default.Devices
                },
                contentDescription = title,
                tint = if (backgroundColor == AppColors.CardBackground)
                    AppColors.PrimaryGreen else AppColors.TextPrimary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = if (backgroundColor == AppColors.CardBackground)
                    AppColors.PrimaryGreen else AppColors.TextPrimary
            )
        }
    }
}