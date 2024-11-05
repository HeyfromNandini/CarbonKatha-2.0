package project.app.carbonkatha20.presentation.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import project.app.carbonkatha20.ui.theme.AppColors
import kotlin.math.*
import kotlin.random.Random

@OptIn(ExperimentalTextApi::class)
@Composable
fun FinalSplashScreen(onSplashComplete: () -> Unit) {
    val scope = rememberCoroutineScope()
    
    // Earth Animation
    val earthRotation = remember { Animatable(0f) }
    val earthScale = remember { Animatable(0f) }
    
    // Eco System Particles
    val ecoParticles = remember { List(150) { EcoParticle() } }
    
    // Sustainability Ring
    val ringProgress = remember { Animatable(0f) }
    val ringRotation = remember { Animatable(0f) }
    
    // Text Animations
    val titleScale = remember { Animatable(0f) }
    val subtitleAlpha = remember { Animatable(0f) }
    
    // Pulse Effect
    val pulseScale = remember { Animatable(1f) }

    LaunchedEffect(true) {
        scope.launch {
            // Initial Earth appearance
            launch {
                earthScale.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            }
            
            // Continuous Earth rotation
            launch {
                earthRotation.animateTo(
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(20000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
            
            // Sustainability ring animation
            launch {
                ringProgress.animateTo(1f, tween(2000))
                ringRotation.animateTo(
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(10000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
            
            // Pulse animation
            launch {
                while(true) {
                    pulseScale.animateTo(1.2f, tween(1000))
                    pulseScale.animateTo(1f, tween(1000))
                }
            }
            
            // Text animations
            delay(500)
            titleScale.animateTo(1f, spring(Spring.DampingRatioMediumBouncy))
            delay(300)
            subtitleAlpha.animateTo(1f, tween(1000))
            
            delay(3000)
            onSplashComplete()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // Background stars
        Canvas(modifier = Modifier.fillMaxSize()) {
            repeat(100) {
                val x = Random.nextFloat() * size.width
                val y = Random.nextFloat() * size.height
                drawCircle(
                    color = Color.White,
                    radius = Random.nextFloat() * 2f,
                    center = Offset(x, y),
                    alpha = Random.nextFloat()
                )
            }
        }

        // Main Earth and Eco System
        Canvas(modifier = Modifier
            .fillMaxSize()
            .scale(earthScale.value)
            .rotate(earthRotation.value)
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val earthRadius = size.width * 0.2f

            // Draw Earth
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1B4F72), // Deep ocean
                        Color(0xFF2ECC71), // Land
                        Color(0xFF1D8348)  // Forests
                    ),
                    center = center,
                    radius = earthRadius
                ),
                radius = earthRadius,
                center = center
            )

            // Draw atmosphere glow
            drawCircle(
                color = AppColors.PrimaryGreen.copy(alpha = 0.2f),
                radius = earthRadius * 1.1f,
                center = center,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )

            // Sustainability Ring
            val ringRadius = earthRadius * 1.3f
            for (i in 0..360 step 5) {
                val angle = i * PI / 180
                val x = center.x + cos(angle) * ringRadius
                val y = center.y + sin(angle) * ringRadius
                
                drawCircle(
                    color = AppColors.AccentGold,
                    radius = 3f,
                    center = Offset(x.toFloat(), y.toFloat()),
                    alpha = ringProgress.value
                )
            }
        }

        // Eco Particles
        Canvas(modifier = Modifier.fillMaxSize()) {
            ecoParticles.forEach { particle ->
                particle.update(size)
                drawCircle(
                    color = particle.color,
                    radius = particle.size,
                    center = Offset(particle.x, particle.y),
                    alpha = particle.alpha
                )
                
                // Draw connecting lines between nearby particles
                ecoParticles.forEach { other ->
                    val distance = sqrt(
                        (particle.x - other.x).pow(2) +
                        (particle.y - other.y).pow(2)
                    )
                    if (distance < 100f) {
                        drawLine(
                            color = AppColors.PrimaryGreen,
                            start = Offset(particle.x, particle.y),
                            end = Offset(other.x, other.y),
                            alpha = (1f - distance / 100f) * 0.2f
                        )
                    }
                }
            }
        }

        // App Title and Tagline
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .scale(titleScale.value)
                .blur(radius = (1f - titleScale.value) * 10.dp)
        ) {
            Text(
                text = "CARBON",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "KATHA",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.PrimaryGreen,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your Journey to Sustainability",
                fontSize = 16.sp,
                color = AppColors.TextSecondary,
                modifier = Modifier.graphicsLayer {
                    alpha = subtitleAlpha.value
                }
            )
        }
    }
}

private class EcoParticle {
    var x: Float = Random.nextFloat() * 1000
    var y: Float = Random.nextFloat() * 2000
    var speedX: Float = Random.nextFloat() * 2 - 1
    var speedY: Float = Random.nextFloat() * 2 - 1
    var size: Float = Random.nextFloat() * 4 + 2
    var alpha: Float = Random.nextFloat()
    var color: Color = when (Random.nextInt(3)) {
        0 -> AppColors.PrimaryGreen
        1 -> AppColors.AccentGold
        else -> Color.White
    }

    fun update(canvasSize: Size) {
        x = (x + speedX).mod(canvasSize.width)
        y = (y + speedY).mod(canvasSize.height)
        alpha = (alpha + Random.nextFloat() * 0.1f - 0.05f)
            .coerceIn(0.2f, 0.8f)
    }
} 