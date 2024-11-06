package project.app.carbonkatha20.presentation.profile.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import project.app.carbonkatha20.ui.theme.AppColors

@Composable
fun ImpactChart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        )
    ) {
        Canvas(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            val width = size.width
            val height = size.height
            val path = Path()
            
            // Sample data points for the chart
            val points = listOf(
                0.2f, 0.4f, 0.3f, 0.6f, 0.5f, 0.7f, 0.8f
            )
            
            // Draw chart line
            path.moveTo(0f, height * (1f - points[0]))
            points.forEachIndexed { index, point ->
                val x = width * (index.toFloat() / (points.size - 1))
                val y = height * (1f - point)
                path.lineTo(x, y)
            }
            
            drawPath(
                path = path,
                color = AppColors.PrimaryGreen,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 3f
                )
            )
        }
    }
}

@Composable
fun ImpactStatistics() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        StatisticItem(
            title = "Monthly Carbon Reduction",
            value = "32.5 kg",
            trend = "+15% vs last month"
        )
        StatisticItem(
            title = "Sustainable Fashion Choices",
            value = "18 items",
            trend = "Saved 45kg CO₂"
        )
        StatisticItem(
            title = "Green Transport Usage",
            value = "85%",
            trend = "Reduced 28kg CO₂"
        )
    }
}

@Composable
private fun StatisticItem(
    title: String,
    value: String,
    trend: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = AppColors.TextPrimary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall,
                    color = AppColors.PrimaryGreen
                )
                Text(
                    text = trend,
                    style = MaterialTheme.typography.bodySmall,
                    color = AppColors.TextSecondary
                )
            }
        }
    }
} 