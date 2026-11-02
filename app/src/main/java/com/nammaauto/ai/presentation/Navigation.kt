package com.nammaauto.ai.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nammaauto.ai.core.theme.AutoYellow
import com.nammaauto.ai.core.theme.Slate400
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.presentation.calculator.FareEstimatorScreen
import com.nammaauto.ai.presentation.calculator.FareEstimatorViewModel
import com.nammaauto.ai.presentation.cost.TravelCostCalculatorScreen
import com.nammaauto.ai.presentation.cost.TravelCostViewModel
import com.nammaauto.ai.presentation.favorites.FavoritesScreen
import com.nammaauto.ai.presentation.favorites.FavoritesViewModel
import com.nammaauto.ai.presentation.food.VegFoodScreen
import com.nammaauto.ai.presentation.food.VegFoodViewModel
import com.nammaauto.ai.presentation.lifeline.LifelineScreen
import com.nammaauto.ai.presentation.lifeline.LifelineViewModel
import com.nammaauto.ai.presentation.meter.MeterCamScreen
import com.nammaauto.ai.presentation.meter.MeterViewModel
import com.nammaauto.ai.presentation.phrasebook.KannadaSathiScreen
import com.nammaauto.ai.presentation.phrasebook.KannadaSathiViewModel
import com.nammaauto.ai.presentation.scraper.LiveTravelIntelScreen
import com.nammaauto.ai.presentation.scraper.LiveTravelScraperViewModel
import com.nammaauto.ai.presentation.store.ProStoreScreen
import com.nammaauto.ai.presentation.store.ProStoreViewModel
import com.nammaauto.ai.presentation.tourism.KarnatakaExplorerScreen
import com.nammaauto.ai.presentation.tourism.KarnatakaExplorerViewModel
import com.nammaauto.ai.presentation.tourism.KarnatakaMediaShowcaseScreen
import com.nammaauto.ai.presentation.tourism.StatewideTravelNavigatorScreen

sealed class AppDestination(val route: String, val label: String, val icon: ImageVector) {
    data object ExploreKA : AppDestination("explore_ka", "Explore KA", Icons.Default.Explore)
    data object VegFood : AppDestination("veg_food", "Veg Dining", Icons.Default.Restaurant)
    data object LiveIntel : AppDestination("live_intel", "Live Intel", Icons.Default.Public)
    data object TripCosts : AppDestination("trip_costs", "Trip Costs", Icons.Default.Calculate)
    data object Favorites : AppDestination("favorites", "Saved", Icons.Default.Bookmark)

    // Companion Utility Routes
    data object MediaGallery : AppDestination("media_gallery", "Photos & 4K", Icons.Default.SmartDisplay)
    data object TransitGuide : AppDestination("transit_guide", "Transit Hub", Icons.Default.DirectionsBus)
    data object KannadaSathi : AppDestination("kannada", "Kannada Voice", Icons.Default.RecordVoiceOver)
    data object Lifeline : AppDestination("lifeline", "SafeGuard SOS", Icons.Default.HealthAndSafety)
    data object MeterCam : AppDestination("meter_cam", "Meter", Icons.Default.Speed)
    data object FairFare : AppDestination("fair_fare", "Ride Radar", Icons.Default.Calculate)
    data object ProStore : AppDestination("pro_store", "Pro Pass", Icons.Default.Star)
}

@Composable
fun NammaAutoNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val primaryDestinations = listOf(
        AppDestination.ExploreKA,
        AppDestination.VegFood,
        AppDestination.LiveIntel,
        AppDestination.TripCosts,
        AppDestination.Favorites
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Slate800,
                contentColor = Slate400
            ) {
                primaryDestinations.forEach { dest ->
                    val isSelected = currentRoute == dest.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(dest.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = dest.icon, contentDescription = dest.label)
                        },
                        label = {
                            Text(text = dest.label)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Slate900,
                            selectedTextColor = AutoYellow,
                            indicatorColor = AutoYellow,
                            unselectedIconColor = Slate400,
                            unselectedTextColor = Slate400
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.ExploreKA.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppDestination.ExploreKA.route) {
                val vm: KarnatakaExplorerViewModel = viewModel()
                KarnatakaExplorerScreen(
                    viewModel = vm,
                    onNavigateToPhrasebook = {
                        navController.navigate(AppDestination.KannadaSathi.route)
                    },
                    onNavigateToProStore = {
                        navController.navigate(AppDestination.ProStore.route)
                    }
                )
            }
            composable(AppDestination.VegFood.route) {
                val vm: VegFoodViewModel = viewModel()
                VegFoodScreen(viewModel = vm)
            }
            composable(AppDestination.LiveIntel.route) {
                val vm: LiveTravelScraperViewModel = viewModel()
                LiveTravelIntelScreen(viewModel = vm)
            }
            composable(AppDestination.TripCosts.route) {
                val vm: TravelCostViewModel = viewModel()
                TravelCostCalculatorScreen(viewModel = vm)
            }
            composable(AppDestination.Favorites.route) {
                val vm: FavoritesViewModel = viewModel()
                FavoritesScreen(
                    viewModel = vm,
                    onNavigateToExplore = {
                        navController.navigate(AppDestination.ExploreKA.route)
                    },
                    onNavigateToVegFood = {
                        navController.navigate(AppDestination.VegFood.route)
                    }
                )
            }
            composable(AppDestination.MediaGallery.route) {
                KarnatakaMediaShowcaseScreen()
            }
            composable(AppDestination.TransitGuide.route) {
                StatewideTravelNavigatorScreen()
            }
            composable(AppDestination.KannadaSathi.route) {
                val vm: KannadaSathiViewModel = viewModel()
                KannadaSathiScreen(
                    viewModel = vm,
                    onNavigateToProStore = {
                        navController.navigate(AppDestination.ProStore.route)
                    }
                )
            }
            composable(AppDestination.Lifeline.route) {
                val vm: LifelineViewModel = viewModel()
                LifelineScreen(viewModel = vm)
            }
            composable(AppDestination.MeterCam.route) {
                val vm: MeterViewModel = viewModel()
                MeterCamScreen(
                    viewModel = vm,
                    onNavigateToProStore = {
                        navController.navigate(AppDestination.ProStore.route)
                    }
                )
            }
            composable(AppDestination.FairFare.route) {
                val vm: FareEstimatorViewModel = viewModel()
                FareEstimatorScreen(
                    viewModel = vm,
                    onOpenKannadaNegotiator = {
                        navController.navigate(AppDestination.KannadaSathi.route)
                    }
                )
            }
            composable(AppDestination.ProStore.route) {
                val vm: ProStoreViewModel = viewModel()
                ProStoreScreen(viewModel = vm)
            }
        }
    }
}
