package project.app.carbonkatha20.presentation.food.receipedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    var quantity by remember { mutableStateOf(8) }
    var showImageGallery by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        // Reviews Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "REVIEWS",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = "READ >",
                color = Color.Red,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.clickable { /* Handle read more */ }
            )
        }

        // Image Gallery Preview
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(3) { index ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("your_image_url_$index")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
                    .clickable { showImageGallery = true },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+2",
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Difficulty Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "DIFFICULTY: Easy",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Color.Black
                )
            )
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Time Indicators
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TimeIndicator(
                label = "Cooking",
                time = "30 min.",
                progress = 0.75f // 75% progress
            )
            TimeIndicator(
                label = "Baking",
                time = "20 min.",
                progress = 0.5f // 50% progress
            )
            TimeIndicator(
                label = "Resting",
                time = "35 min.",
                progress = 0.25f // 25% progress
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Ingredients Section
        Text(
            text = "INGREDIENTS",
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quantity Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$quantity pieces",
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Black
                )
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { if (quantity > 1) quantity-- },
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = { quantity++ },
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredients List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ingredients) { ingredient ->
                IngredientItem(
                    ingredient = ingredient,
                    quantity = quantity
                )
            }
        }

        // Start Cooking Button
        Button(
            onClick = { /* Handle start cooking */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF2E7D32)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Start cooking!",
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }

    if (showImageGallery) {
        ImageGalleryDialog(
            images = listOf("url1", "url2", "url3", "url4", "url5"),
            onDismiss = { showImageGallery = false }
        )
    }
}

@Composable
private fun TimeIndicator(
    label: String,
    time: String,
    progress: Float = 0f // Add progress parameter
) {
    var progressAnimation by remember { mutableStateOf(0f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progressAnimation,
        animationSpec = tween(durationMillis = 1000),
        label = "Progress Animation"
    )

    LaunchedEffect(Unit) {
        progressAnimation = progress
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier.size(60.dp),
            contentAlignment = Alignment.Center
        ) {
            // Background Circle
            Canvas(modifier = Modifier.size(60.dp)) {
                drawCircle(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            
            // Progress Circle
            Canvas(modifier = Modifier.size(60.dp)) {
                drawArc(
                    color = Color(0xFF2E7D32), // Green color
                    startAngle = -90f,
                    sweepAngle = animatedProgress.value * 360f,
                    useCenter = false,
                    style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            
            // Time Text
            Text(
                text = time,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            text = label,
            color = Color.Black,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
private fun IngredientItem(
    ingredient: Ingredient,
    quantity: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${ingredient.amount * quantity / 8}${ingredient.unit}",
            style = MaterialTheme.typography.body2.copy(color = Color.Black)
        )
        Text(
            text = ingredient.name,
            style = MaterialTheme.typography.body2.copy(color = Color.Black)
        )
    }
}

@Composable
private fun ImageGalleryDialog(
    images: List<String>,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(images) { imageUrl ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

data class Ingredient(
    val amount: Int,
    val unit: String,
    val name: String
)

private val ingredients = listOf(
    Ingredient(400, "g", "flour"),
    Ingredient(100, "g", "whole-wheat flour"),
    Ingredient(42, "g", "fresh yeast"),
    Ingredient(1, "tbsp", "sugar"),
    Ingredient(300, "ml", "water (lukewarm)"),
    Ingredient(20, "g", "olive oil"),
    Ingredient(10, "g", "salt")
) 