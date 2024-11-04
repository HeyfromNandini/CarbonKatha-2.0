package project.app.carbonkatha20.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.ui.layout.ContentScale
import project.app.carbonkatha20.R
import project.app.carbonkatha20.utils.ProgressSection

val DarkGreen = Color(0xFF1E5631)

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top Bar with Profile and Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            
            Row {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = DarkGreen
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Grid",
                    tint = DarkGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Weather Section
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "25°",
                fontSize = 20.sp,
                color = DarkGreen
            )
            Text(
                text = "Cloudy",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(16.dp))
            Surface(
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "New Jersey",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Greeting
        Text(
            text = "Good Morning",
            fontSize = 24.sp,
            color = DarkGreen,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "let's start the day with some new plans",
            fontSize = 20.sp,
            color = DarkGreen
        )

        Spacer(modifier = Modifier.height(24.dp))

        // App Shortcuts Grid
        AppShortcutsGrid()

        Spacer(modifier = Modifier.height(24.dp))

        // Progress Section
        ProgressSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Suggested Section
        SuggestedSection()
    }
}

@Composable
fun AppShortcutsGrid() {
    // Implementation for 4x2 grid of app shortcuts
    // Add your app icons and labels here
}

@Composable
fun SuggestedSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Suggested",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGreen
            )
            Text(
                text = "See all →",
                color = DarkGreen
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipeItems) { recipe ->
                RecipeCard(
                    title = recipe.title,
                    duration = recipe.duration,
                    imageResId = recipe.imageResId
                )
            }
        }
    }
}

@Composable
fun RecipeCard(title: String, duration: String, imageResId: Int) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            // Recipe Image
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGreen
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Duration",
                    tint = DarkGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = duration,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}



// Define RecipeItem data class
data class RecipeItem(
    val title: String,
    val duration: String,
    val imageResId: Int
)


// Sample recipe items
private val recipeItems = listOf(
    RecipeItem(
        title = "Make a\nSalad today",
        duration = "10 minutes",
        imageResId = R.drawable.ic_launcher_background
    ),
    RecipeItem(
        title = "Make a\nSalad today",
        duration = "20 minutes",
        imageResId = R.drawable.ic_launcher_background
    )
)
