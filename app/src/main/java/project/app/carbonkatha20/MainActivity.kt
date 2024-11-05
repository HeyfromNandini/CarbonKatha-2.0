package project.app.carbonkatha20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import project.app.carbonkatha20.navigation.NavGraph
import project.app.carbonkatha20.presentation.components.AnimatedBottomBar
import project.app.carbonkatha20.ui.theme.CarbonKatha20Theme
import project.app.carbonkatha20.presentation.splash.CarbonKathaSplashScreen
import project.app.carbonkatha20.presentation.splash.CrazyKathaSplashScreen
import project.app.carbonkatha20.presentation.splash.FinalSplashScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CarbonKatha20Theme {
                var showSplash by remember { mutableStateOf(true) }
                
                if (showSplash) {
                    CrazyKathaSplashScreen(
                        onSplashComplete = {
                            showSplash = false
                        }
                    )
                } else {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var bottomBarState by remember { mutableStateOf(true) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                AnimatedBottomBar(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                NavGraph(
                    navController = navController,
                    onBottomBarVisibilityChange = { isVisible ->
                        bottomBarState = isVisible
                    }
                )
            }
        }
    }
}
