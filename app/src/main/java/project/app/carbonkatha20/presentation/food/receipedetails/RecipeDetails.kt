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
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Brush
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.Card
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.TopAppBar


@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Recipe Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-vegetables.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // White Overlay Card
            Card(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 50.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "RECIPE",
                        style = MaterialTheme.typography.overline,
                        color = Color.Gray
                    )
                    
                    Text(
                        text = "Egg cheese recipe",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Timer Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "20 mins",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(50.dp))
        
        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            backgroundColor = Color.White,
            contentColor = Color(0xFF2196F3),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Color(0xFF2196F3),
                    height = 2.dp
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Ingredients") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Instructions") }
            )
        }
        
        // Content based on selected tab
        when (selectedTab) {
            0 -> IngredientsContent()
            1 -> InstructionsContent()
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Bottom Navigation
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 16.dp
        ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, "Home") },
                label = { Text("Home") },
                selected = false,
                onClick = { }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Timer, "Food") },
                label = { Text("Food") },
                selected = true,
                onClick = { }
            )
            BottomNavigationItem(
                icon = { 
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Fashion",
                    )
                },
                label = { Text("Fashion") },
                selected = false,
                onClick = { }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Share, "Technology") },
                label = { Text("Technology") },
                selected = false,
                onClick = { }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Person, "Profile") },
                label = { Text("Profile") },
                selected = false,
                onClick = { }
            )
        }
    }
}

@Composable
private fun IngredientsContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(ingredients) { ingredient ->
            Text(
                text = ingredient,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun InstructionsContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(instructions) { instruction ->
            Text(
                text = instruction,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

private val ingredients = listOf(
    "2 slices of bread",
    "Butter",
    "Shredded cheese",
    "Spinach leaves",
    "Tomato slices (optional)",
    "Avocado slices (optional)",
    "Salt and pepper to taste"
)

private val instructions = listOf(
    "Preheat a skillet or griddle over medium heat.",
    "Take two slices of bread and spread butter on one side of each slice.",
    "Place the buttered side down on the skillet or griddle.",
    "Divide the shredded cheese evenly between the two slices of bread on the skillet.",
    "Place a handful of spinach leaves on top of the cheese on each slice.",
    "If desired, add slices of tomato or avocado on top of the spinach.",
    "Season with salt and pepper to taste.",
    "Place the remaining slices of bread on top of"
) 