package project.app.carbonkatha20.presentation.chat

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import project.app.carbonkatha20.presentation.chat.effects.CircuitLines
import project.app.carbonkatha20.presentation.chat.effects.NeuralNetworkEffect
import project.app.carbonkatha20.presentation.chat.effects.ParticleEffect
import kotlin.random.Random

@Composable
fun TechChatScreen() {
    var message by remember { mutableStateOf("") }
    val particles = remember { List(20) { TechParticle() } }
    val botThinking = remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Animated background particles
        particles.forEach { particle ->
            ParticleEffect(particle)
        }

        // Neural network visualization
        NeuralNetworkEffect()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Futuristic Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                // Animated circuit lines
                CircuitLines()
                
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "TECH BOT",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00FF94),
                        modifier = Modifier.blur(radius = if (botThinking.value) 2.dp else 0.dp)
                    )
                    Text(
                        text = "Sustainable Tech Assistant",
                        fontSize = 14.sp,
                        color = Color(0xFF00FF94).copy(alpha = 0.7f)
                    )
                }
            }

            // Chat messages
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                items(sampleMessages) { message ->
                    MessageBubble(message)
                }
            }

            // Futuristic Input Field
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xFF001F3F))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = message,
                        onValueChange = { message = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedTextColor = Color(0xFF00FF94),
                            focusedTextColor = Color(0xFF00FF94)
                        ),
                        placeholder = {
                            Text(
                                "Ask about sustainable tech...",
                                color = Color(0xFF00FF94).copy(alpha = 0.5f)
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                    
                    FloatingActionButton(
                        onClick = { /* Handle send */ },
                        containerColor = Color(0xFF00FF94),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Send",
                            tint = Color.Black
                        )
                    }
                }
            }
        }

        // Floating Tech Elements
        FloatingTechElements()
    }
}

@Composable
private fun MessageBubble(message: ChatMessage) {
    val alignment = if (message.isBot) Alignment.CenterStart else Alignment.CenterEnd
    val bubbleColor = if (message.isBot) Color(0xFF001F3F) else Color(0xFF00FF94)
    val textColor = if (message.isBot) Color(0xFF00FF94) else Color.Black
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = alignment
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(bubbleColor)
                .padding(16.dp)
                .widthIn(max = 280.dp)
        ) {
            Text(
                text = message.content,
                color = textColor,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun FloatingTechElements() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing)
        )
    )
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Add floating tech-themed elements
        repeat(5) { index ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .rotate(rotation.value + (index * 72))
                    .offset(
                        x = (200 + index * 50).dp,
                        y = (100 + index * 30).dp
                    )
                    .background(
                        Color(0xFF00FF94).copy(alpha = 0.2f),
                        CircleShape
                    )
            )
        }
    }
}

class TechParticle {
    var x: Float = Random.nextFloat() * 1000
    var y: Float = Random.nextFloat() * 2000
    var size: Float = Random.nextFloat() * 4 + 2
}

data class ChatMessage(
    val content: String,
    val isBot: Boolean
)

val sampleMessages = listOf(
    ChatMessage("How can I reduce my laptop's energy consumption?", false),
    ChatMessage("To reduce your laptop's energy consumption:\n• Adjust screen brightness\n• Use power-saving mode\n• Close unused applications\n• Update your system regularly", true),
    ChatMessage("What's the most eco-friendly way to dispose of old electronics?", false),
    ChatMessage("For eco-friendly electronics disposal:\n• Use certified e-waste recyclers\n• Consider donating working devices\n• Remove personal data before disposal\n• Check manufacturer take-back programs", true)
) 