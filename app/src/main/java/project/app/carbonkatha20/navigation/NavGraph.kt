package project.app.carbonkatha20.navigation

import DashboardScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import project.app.carbonkatha20.presentation.FoodChatScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
    onBottomBarVisibilityChange: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            DashboardScreen()
            onBottomBarVisibilityChange(true)
        }
        
        composable(Screen.Food.route) {
            FoodChatScreen()
            onBottomBarVisibilityChange(true)
        }
        
        composable(Screen.Fashion.route) {
//            FashionScreen()
//            onBottomBarVisibilityChange(true)
        }
        
        composable(Screen.Tech.route) {
//            TechScreen()
//            onBottomBarVisibilityChange(true)
        }
        
        composable(Screen.Profile.route) {
//            ProfileScreen()
//            onBottomBarVisibilityChange(true)
        }
        
        composable(
            route = Screen.RecipeDetail.route
        ) {
            // RecipeDetailScreen()
            onBottomBarVisibilityChange(false) // Hide bottom bar in detail screen
        }
    }
} 