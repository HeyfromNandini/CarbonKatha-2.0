package project.app.carbonkatha20.presentation.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import project.app.carbonkatha20.ui.theme.AppColors
import kotlin.math.*
import kotlin.random.Random

@OptIn(ExperimentalTextApi::class)
@Composable
fun CrazyKathaSplashScreen(onSplashComplete: () -> Unit) {
    val scope = rememberCoroutineScope()
    
    // DNA Helix Animation
    val dnaRotation = remember { Animatable(0f) }
    val dnaScale = remember { Animatable(0f) }
    
    // Particle System
    val particles = remember {
        List(100) { ParticleEffect() }
    }
    
    // Morphing Animation
    val morphProgress = remember { Animatable(0f) }
    
    // Energy Wave
    val wavePhase = remember { Animatable(0f) }
    
    // 3D rotation
    val rotation3D = remember { Animatable(0f) }
    
    // Text Animation
    val textGlow = remember { Animatable(0f) }
    
    LaunchedEffect(true) {
        scope.launch {
            // Initial DNA formation
            launch {
                dnaScale.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            
            launch {
                // Continuous DNA rotation
                dnaRotation.animateTo(
                    targetValue = 720f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(3000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
            
            // Energy wave pulse
            launch {
                wavePhase.animateTo(
                    targetValue = PI.toFloat() * 2,
                    animationSpec = infiniteRepeatable(
                        animation = tween(2000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
            
            // 3D rotation effect
            launch {
                rotation3D.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(3000, easing = LinearOutSlowInEasing)
                )
            }
            
            // Text glow effect
            launch {
                textGlow.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1000),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }
            
            delay(1000)
            
            // Morph DNA into tree
            morphProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(2000, easing = FastOutSlowInEasing)
            )
            
            delay(1000)
            onSplashComplete()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .rotate(rotation3D.value),
        contentAlignment = Alignment.Center
    ) {
        // Main Canvas for DNA and Particles
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            
            // Draw Energy Waves
            for (i in 0..5) {
                drawCircle(
                    color = AppColors.PrimaryGreen.copy(alpha = 0.2f),
                    radius = 100f + i * 50f + sin(wavePhase.value) * 20f,
                    center = center,
                    style = Stroke(width = 2f)
                )
            }
            
            // Draw DNA/Tree Hybrid
            for (i in 0..360 step 5) {
                val angle = i * PI / 180
                val radius = 150f
                
                // DNA Strands morphing into tree branches
                val x1 = center.x + cos(angle + dnaRotation.value * PI / 180) * radius
                val y1 = center.y + sin(angle) * radius * (1 - morphProgress.value)
                
                val x2 = center.x + cos(angle + PI + dnaRotation.value * PI / 180) * radius
                val y2 = center.y + sin(angle) * radius * (1 - morphProgress.value)
                
                // DNA connections morphing into leaves
                drawCircle(
                    color = AppColors.PrimaryGreen,
                    radius = (5f + sin(angle) * 3f).toFloat(),
                    center = Offset(x1.toFloat(), y1.toFloat()),
                    alpha = dnaScale.value
                )
                
                drawCircle(
                    color = AppColors.AccentGold,
                    radius = (5f + cos(angle) * 3f).toFloat(),
                    center = Offset(x2.toFloat(), y2.toFloat()),
                    alpha = dnaScale.value
                )
                
                // Tree formation
                if (morphProgress.value > 0) {
                    val treeX = center.x + cos(angle) * (radius * morphProgress.value)
                    val treeY = center.y - sin(angle) * (radius * morphProgress.value)
                    
                    drawLine(
                        color = AppColors.PrimaryGreen,
                        start = center,
                        end = Offset(treeX.toFloat(), treeY.toFloat()),
                        strokeWidth = 2f,
                        alpha = morphProgress.value
                    )
                    
                    // Leaves
                    drawCircle(
                        color = AppColors.PrimaryGreen.copy(alpha = 0.5f),
                        radius = 10f * morphProgress.value,
                        center = Offset(treeX.toFloat(), treeY.toFloat())
                    )
                }
            }
            
            // Particle Effects
            particles.forEach { particle ->
                particle.update()
                drawCircle(
                    color = particle.color,
                    radius = particle.size,
                    center = Offset(particle.x, particle.y),
                    alpha = particle.alpha
                )
            }
        }
        
        // Animated Text
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(dnaScale.value)
        ) {
            Text(
                text = "CARBON",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = textGlow.value),
                textAlign = TextAlign.Center,
                modifier = Modifier.graphicsLayer {
                    shadowElevation = 20f
                    shape = RoundedCornerShape(16.dp)
                    clip = true
                }
            )
            Text(
                text = "KATHA",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.PrimaryGreen.copy(alpha = textGlow.value),
                textAlign = TextAlign.Center
            )
        }
    }
}

private class ParticleEffect {
    var x: Float = Random.nextFloat() * 1000
    var y: Float = Random.nextFloat() * 2000
    var speedX: Float = Random.nextFloat() * 4 - 2
    var speedY: Float = Random.nextFloat() * 4 - 2
    var size: Float = Random.nextFloat() * 5 + 2
    var alpha: Float = Random.nextFloat()
    var color: Color = when (Random.nextInt(3)) {
        0 -> AppColors.PrimaryGreen
        1 -> AppColors.AccentGold
        else -> Color.White
    }

    fun update() {
        x += speedX
        y += speedY
        alpha = (alpha - 0.01f).coerceAtLeast(0f)
        if (alpha <= 0f) {
            x = Random.nextFloat() * 1000
            y = Random.nextFloat() * 2000
            alpha = 1f
            speedX = Random.nextFloat() * 4 - 2
            speedY = Random.nextFloat() * 4 - 2
        }
    }
} 