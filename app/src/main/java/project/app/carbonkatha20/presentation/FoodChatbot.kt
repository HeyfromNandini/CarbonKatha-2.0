package project.app.carbonkatha20.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import project.app.carbonkatha20.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodChatScreen() {
    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        // Simulate loading delay
        delay(2000)
        isLoading = false
    }
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF8F9FA), // Light grayish white
            Color(0xFFFFE5E5)  // Very light pink
        )
    )

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle history */ }) {
                        Icon(
                            Icons.Default.History,
                            contentDescription = "History",
                            tint = Color.Gray
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Title
                Text(
                    text = "Assist me to make Steak and Kidney Pie for lunch",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dish Section with separate card for title and content
                DishSection()

                Spacer(modifier = Modifier.height(24.dp))

                if (isLoading) {
                    LoadingIndicator()
                } else {
                    Text(
                        text = "Here is how to cook it",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Recipe Steps in LazyRow
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(recipeSteps) { step ->
                            RecipeStep(
                                stepNumber = step.number,
                                description = step.description
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Chat Input
                ChatInput()
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ), label = "loading"
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun RecipeStep(
    stepNumber: Int,
    description: String
) {
    Card(
        modifier = Modifier.width(300.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Step number with icon in card
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0F3F8) // Light grayish blue
                ),
                modifier = Modifier.wrapContentSize()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.DarkGray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "STEP $stepNumber:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Description text with yellowish-greenish gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFF0), // Very light yellow
                                Color(0xFFF5F9F0)  // Light yellowish-green
                            )
                        )
                    )
            ) {
                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = Color.DarkGray.copy(alpha = 0.8f),
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ChatInput() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Chat Avatar",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "What do you want?",
                color = Color.Gray
            )
        }
    }
}

// Recipe step data class
data class RecipeSteps(
    val number: Int,
    val description: String
)

// Dummy recipe steps
val recipeSteps = listOf(
    RecipeSteps(1, "It's important to cook the meat a day ahead, so that you can discard any fat that has risen to the top, and so that the pastry doesn't slump in the face of a too-warm filling, so up to 48 hours ahead -make the pastry."),
    RecipeSteps(2, "Whizz the flour and a pinch of fine sea salt together for a few seconds in a food processor, then add the butter and whizz."),
    RecipeSteps(3, "Add water gradually while pulsing, until the mixture starts coming together."),
    // Add more steps as needed
)

// Dish Section with separate card for title and content
@Composable
fun DishSection() {
    Column {
        Text(
            text = "Dish",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Steak and Kidney Pie",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Steak and Kidney Pie",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}