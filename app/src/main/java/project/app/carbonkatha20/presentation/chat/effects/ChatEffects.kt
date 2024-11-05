package project.app.carbonkatha20.presentation.chat.effects

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import project.app.carbonkatha20.presentation.chat.TechParticle
import kotlin.math.*
import kotlin.random.Random

@Composable
fun ParticleEffect(particle: TechParticle) {
    val infiniteTransition = rememberInfiniteTransition()
    val particleAlpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color(0xFF00FF94),
            radius = particle.size,
            center = Offset(particle.x, particle.y),
            alpha = particleAlpha * 0.5f
        )
        
        // Draw connecting lines between nearby particles
        if (Random.nextFloat() > 0.7f) {
            drawLine(
                color = Color(0xFF00FF94),
                start = Offset(particle.x, particle.y),
                end = Offset(
                    particle.x + Random.nextFloat() * 100,
                    particle.y + Random.nextFloat() * 100
                ),
                alpha = particleAlpha * 0.2f
            )
        }
    }
}

@Composable
fun NeuralNetworkEffect() {
    val nodes = remember {
        List(15) {
            NeuralNode(
                x = Random.nextFloat() * 1000,
                y = Random.nextFloat() * 2000
            )
        }
    }
    
    val infiniteTransition = rememberInfiniteTransition()
    val pulseState by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        nodes.forEach { node ->
            // Draw nodes
            drawCircle(
                color = Color(0xFF00FF94),
                radius = 4.dp.toPx(),
                center = Offset(node.x, node.y),
                alpha = 0.5f
            )
            
            // Draw neural connections
            nodes.forEach { otherNode ->
                val distance = sqrt(
                    (node.x - otherNode.x).pow(2) +
                    (node.y - otherNode.y).pow(2)
                )
                if (distance < 300) {
                    drawLine(
                        color = Color(0xFF00FF94),
                        start = Offset(node.x, node.y),
                        end = Offset(otherNode.x, otherNode.y),
                        alpha = (1f - distance / 300f) * 0.2f * pulseState
                    )
                }
            }
        }
    }
}

@Composable
fun CircuitLines() {
    val infiniteTransition = rememberInfiniteTransition()
    val circuitProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val width = size.width
        val height = size.height
        
        // Draw main circuit paths
        val path = Path().apply {
            moveTo(0f, height * 0.5f)
            relativeLineTo(width * 0.2f, 0f)
            relativeLineTo(width * 0.1f, -height * 0.2f)
            relativeLineTo(width * 0.4f, 0f)
            relativeLineTo(width * 0.1f, height * 0.2f)
            relativeLineTo(width * 0.2f, 0f)
        }

        // Draw circuit background
        drawPath(
            path = path,
            color = Color(0xFF00FF94),
            alpha = 0.2f,
            style = Stroke(
                width = 2.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(20f, 10f)
                )
            )
        )

        // Draw animated circuit pulse
        drawPath(
            path = path,
            color = Color(0xFF00FF94),
            alpha = 0.8f,
            style = Stroke(
                width = 2.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(20f, 20f),
                    phase = circuitProgress * 40f
                )
            )
        )

        // Add circuit nodes
        val nodePositions = listOf(
            Offset(width * 0.2f, height * 0.5f),
            Offset(width * 0.5f, height * 0.3f),
            Offset(width * 0.8f, height * 0.5f)
        )

        nodePositions.forEach { position ->
            drawCircle(
                color = Color(0xFF00FF94),
                radius = 4.dp.toPx(),
                center = position,
                alpha = 0.8f
            )
            drawCircle(
                color = Color(0xFF00FF94),
                radius = 8.dp.toPx(),
                center = position,
                alpha = 0.2f * (1f - circuitProgress)
            )
        }
    }
}

private data class NeuralNode(
    val x: Float,
    val y: Float
) 