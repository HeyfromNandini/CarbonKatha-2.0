package project.app.carbonkatha20.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import project.app.carbonkatha20.presentation.fashion.*

sealed class FashionScreens(val route: String) {
    object Home : FashionScreens("fashion_home")
    object GenerateOutfit : FashionScreens("generate_outfit")
    object Wardrobe : FashionScreens("wardrobe")
    object ThriftStores : FashionScreens("thrift_stores")
    object AddItem : FashionScreens("add_item")
}

@Composable
fun FashionNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FashionScreens.Home.route
    ) {
        composable(FashionScreens.Home.route) {
            FashionHomeScreen(
                onGenerateOutfitClick = { navController.navigate(FashionScreens.GenerateOutfit.route) },
                onWardrobeClick = { navController.navigate(FashionScreens.Wardrobe.route) },
                onThriftStoresClick = { navController.navigate(FashionScreens.ThriftStores.route) },
                onAddItemClick = { navController.navigate(FashionScreens.AddItem.route) }
            )
        }
        
        composable(FashionScreens.GenerateOutfit.route) {
            GenerateOutfitScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(FashionScreens.Wardrobe.route) {
            WardrobeScreen(
                onBackClick = { navController.popBackStack() },
                onAddItemClick = { navController.navigate(FashionScreens.AddItem.route) }
            )
        }
        
        composable(FashionScreens.ThriftStores.route) {
            ThriftStoresScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(FashionScreens.AddItem.route) {
            AddWardrobeItemScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
} 