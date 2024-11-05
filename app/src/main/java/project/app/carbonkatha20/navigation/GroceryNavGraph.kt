import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import project.app.carbonkatha20.presentation.FoodChatScreen
import project.app.carbonkatha20.presentation.food.GroceryCategories
import project.app.carbonkatha20.presentation.food.GroceryHomeScreen
import project.app.carbonkatha20.presentation.food.GroceryListScreen
import project.app.carbonkatha20.presentation.food.GroceryViewModel

sealed class GroceryScreens(val route: String) {
    object Home : GroceryScreens("grocery_home")
    object Search : GroceryScreens("grocery_search")
    object Alternatives : GroceryScreens("grocery_alternatives")
    object List : GroceryScreens("grocery_list")
    object Chatbot: GroceryScreens("grocery_chatbot")
}

@Composable
fun GroceryNavGraph() {
    val viewModel: GroceryViewModel = hiltViewModel()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = GroceryScreens.Home.route
    ) {
        composable(GroceryScreens.Home.route) {
            GroceryHomeScreen(
                onSearchClick = { navController.navigate(GroceryScreens.Search.route) },
                onCategoryClick = { navController.navigate(GroceryScreens.List.route) },
                selectedItems = viewModel.selectedItems,
                onMessageClick = { navController.navigate(GroceryScreens.Chatbot.route) }
            )
        }
        
        composable(GroceryScreens.Search.route) {
            GroceryCategories(
                onItemSelected = { item ->
                    viewModel.addItem(item)
                    navController.navigate(GroceryScreens.List.route)
                }
            )
        }
        
        composable(GroceryScreens.List.route) {
            GroceryListScreen(
                onSaveList = {
                    navController.popBackStack(GroceryScreens.Home.route, false)
                }
            )
        }

        composable(GroceryScreens.Chatbot.route) {
            FoodChatScreen()
        }
    }
} 