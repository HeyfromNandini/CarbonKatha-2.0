package project.app.carbonkatha20.navigation

import DashboardScreen
import GroceryNavGraph
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import project.app.carbonkatha20.presentation.FoodChatScreen
import project.app.carbonkatha20.presentation.chat.TechChatScreen
import project.app.carbonkatha20.presentation.food.GroceryCategories
import project.app.carbonkatha20.presentation.food.GroceryHomeScreen
import project.app.carbonkatha20.presentation.food.GroceryListScreen
import project.app.carbonkatha20.presentation.profile.ProfileScreen

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
            GroceryNavGraph()
            onBottomBarVisibilityChange(true)
        }
        
        composable(Screen.Fashion.route) {
            FashionNavGraph()
//            onBottomBarVisibilityChange(true)
        }
        
        composable(Screen.Tech.route) {
            TechChatScreen()
//            onBottomBarVisibilityChange(true)
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen()
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