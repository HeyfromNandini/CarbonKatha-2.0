package project.app.carbonkatha20.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    // Bottom Nav Screens
    object Home : Screen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Food : Screen(
        route = "food_screen",
        title = "Food",
        icon = Icons.Default.Restaurant
    )
    object Fashion : Screen(
        route = "fashion_screen",
        title = "Fashion",
        icon = Icons.Default.Checkroom
    )
    object Tech : Screen(
        route = "tech_screen",
        title = "Tech",
        icon = Icons.Default.Devices
    )
    object Profile : Screen(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Default.Person
    )

    // Other Screens
    object RecipeDetail : Screen(route = "recipe_detail_screen/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe_detail_screen/$recipeId"
    }
}

val bottomNavScreens = listOf(
    Screen.Home,
    Screen.Food,
    Screen.Fashion,
    Screen.Tech,
    Screen.Profile
) 