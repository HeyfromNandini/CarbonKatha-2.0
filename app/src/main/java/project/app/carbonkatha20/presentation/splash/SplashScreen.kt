package project.app.carbonkatha20.presentation.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import project.app.carbonkatha20.ui.theme.AppColors
import kotlin.math.*
import kotlin.random.Random

@Composable
fun CarbonKathaSplashScreen(
    onSplashComplete: () -> Unit
) {
    var isAnimationComplete by remember { mutableStateOf(false) }
    
    // Tree growth animation
    val treeGrowth = remember { Animatable(0f) }
    
    // Floating particles
    val particles = remember {
        List(20) { index ->
            Particle(
                initialX = Random.nextFloat() * 1000f,
                initialY = Random.nextFloat() * 2000f,
                phase = index * 0.1f
            )
        }
    }

    // Logo scale animation
    val logoScale = remember { Animatable(0f) }

    LaunchedEffect(true) {
        // Grow the tree
        treeGrowth.animateTo(
            targetValue = 1f,
            animationSpec = tween(2000, easing = FastOutSlowInEasing)
        )
        
        // Scale up the logo
        logoScale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        
        delay(3000)
        isAnimationComplete = true
        onSplashComplete()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background),
        contentAlignment = Alignment.Center
    ) {
        // Animated tree and particles
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Draw tree trunk
            val trunkHeight = size.height * 0.4f * treeGrowth.value
            val trunkWidth = size.width * 0.05f
            drawRect(
                color = Color(0xFF8B4513),
                topLeft = Offset(
                    size.width / 2 - trunkWidth / 2,
                    size.height * 0.7f - trunkHeight
                ),
                size = androidx.compose.ui.geometry.Size(trunkWidth, trunkHeight)
            )

            // Draw tree leaves
            val leavesPath = Path().apply {
                moveTo(size.width / 2, size.height * 0.3f)
                cubicTo(
                    size.width * 0.2f,
                    size.height * 0.6f,
                    size.width * 0.8f,
                    size.height * 0.6f,
                    size.width / 2,
                    size.height * 0.3f
                )
            }
            
            drawPath(
                path = leavesPath,
                color = AppColors.PrimaryGreen,
                alpha = treeGrowth.value
            )

            // Draw floating particles
            particles.forEach { particle ->
                val particleSize = 20f
                val time = System.currentTimeMillis() / 1000f
                val x = particle.initialX + sin(time + particle.phase) * 50
                val y = (particle.initialY - time * 100) % size.height
                
                drawCircle(
                    color = Color(0xFF4CAF50),
                    radius = particleSize * (0.5f + sin(time + particle.phase) * 0.5f),
                    center = Offset(x, y),
                    alpha = 0.6f
                )
                
                // Draw molecule connections
                drawLine(
                    color = Color(0xFF4CAF50),
                    start = Offset(x, y),
                    end = Offset(
                        x + cos(time) * 30,
                        y + sin(time) * 30
                    ),
                    alpha = 0.3f
                )
            }
        }

        // App name with scaling animation
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(logoScale.value)
        ) {
            Text(
                text = "Carbon",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
            Text(
                text = "Katha",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.PrimaryGreen
            )
        }
    }
}

private data class Particle(
    val initialX: Float,
    val initialY: Float,
    val phase: Float
) 