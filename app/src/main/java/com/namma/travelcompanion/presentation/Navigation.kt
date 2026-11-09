package com.namma.travelcompanion.presentation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.material.icons.filled.MenuBook
import com.namma.travelcompanion.presentation.explore.DistrictScreen
import com.namma.travelcompanion.presentation.explore.DistrictViewModel
import com.namma.travelcompanion.presentation.explore.DistrictsHomeScreen
import com.namma.travelcompanion.presentation.explore.DistrictsHomeViewModel
import com.namma.travelcompanion.presentation.explore.PlaceDetailScreen
import com.namma.travelcompanion.presentation.explore.PlaceDetailViewModel
import androidx.compose.material.icons.filled.Gavel
import com.namma.travelcompanion.feature.commute.presentation.fares.OfficialFaresScreen
import com.namma.travelcompanion.presentation.safety.NightGuardianViewModel
import com.namma.travelcompanion.presentation.safety.NightGuardianScreen
import com.namma.travelcompanion.presentation.hub.HubTile
import com.namma.travelcompanion.presentation.hub.HubScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Commute
import androidx.compose.material.icons.filled.Map
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.namma.travelcompanion.core.theme.AutoYellow
import com.namma.travelcompanion.core.theme.Slate400
import com.namma.travelcompanion.core.theme.Slate800
import com.namma.travelcompanion.core.theme.Slate900
import com.namma.travelcompanion.presentation.calculator.FareEstimatorScreen
import com.namma.travelcompanion.presentation.calculator.FareEstimatorViewModel
import com.namma.travelcompanion.feature.commute.presentation.cost.TravelCostCalculatorScreen
import com.namma.travelcompanion.feature.commute.presentation.cost.TravelCostViewModel
import com.namma.travelcompanion.presentation.favorites.FavoritesScreen
import com.namma.travelcompanion.presentation.favorites.FavoritesViewModel
import com.namma.travelcompanion.presentation.food.VegFoodScreen
import com.namma.travelcompanion.presentation.food.VegFoodViewModel
import com.namma.travelcompanion.feature.commute.presentation.lens.BusSignboardLensScreen
import com.namma.travelcompanion.feature.commute.presentation.lens.BusSignboardLensViewModel
import com.namma.travelcompanion.feature.commute.presentation.lens.LensViewMode
import com.namma.travelcompanion.presentation.lifeline.LifelineScreen
import com.namma.travelcompanion.presentation.lifeline.LifelineViewModel
import com.namma.travelcompanion.feature.commute.presentation.meter.MeterCamScreen
import com.namma.travelcompanion.feature.commute.presentation.meter.MeterViewModel
import com.namma.travelcompanion.presentation.phrasebook.KannadaSathiScreen
import com.namma.travelcompanion.presentation.phrasebook.KannadaSathiViewModel
import com.namma.travelcompanion.feature.commute.presentation.scraper.LiveTravelIntelScreen
import com.namma.travelcompanion.feature.commute.presentation.scraper.LiveTravelScraperViewModel
import com.namma.travelcompanion.presentation.store.ProStoreScreen
import com.namma.travelcompanion.presentation.store.ProStoreViewModel
import com.namma.travelcompanion.presentation.tourism.KarnatakaExplorerScreen
import com.namma.travelcompanion.presentation.tourism.KarnatakaExplorerViewModel
import com.namma.travelcompanion.presentation.tourism.KarnatakaMediaShowcaseScreen
import com.namma.travelcompanion.presentation.tourism.StatewideTravelNavigatorScreen

sealed class AppDestination(val route: String, val label: String, val icon: ImageVector) {
    // Bottom tabs
    data object ExploreKA : AppDestination("explore_ka", "Explore", Icons.Default.Explore)
    data object PlanHub : AppDestination("plan", "Plan", Icons.Default.Map)
    data object CommuteHub : AppDestination("commute", "Commute", Icons.Default.Commute)
    data object KannadaSathi : AppDestination("kannada", "Kannada", Icons.Default.RecordVoiceOver)
    data object SafetyHub : AppDestination("safety", "Safety", Icons.Default.HealthAndSafety)

    // Feature screens
    data object TravelGuide : AppDestination("travel_guide", "Travel Guide & Tips", Icons.Default.MenuBook)
    data object VegFood : AppDestination("veg_food", "Veg Dining", Icons.Default.Restaurant)
    data object LiveIntel : AppDestination("live_intel", "Travel Intel", Icons.Default.Public)
    data object TripCosts : AppDestination("trip_costs", "Trip Costs", Icons.Default.Calculate)
    data object Favorites : AppDestination("favorites", "Saved", Icons.Default.Bookmark)
    data object BusSignLens : AppDestination("bus_sign_lens", "Bus Lens", Icons.Default.CameraAlt)
    data object SignTranslate : AppDestination("sign_translate", "Read a Sign", Icons.Default.Translate)
    data object Phrasebook : AppDestination("phrasebook", "Phrasebook & Translator", Icons.Default.RecordVoiceOver)
    data object MediaGallery : AppDestination("media_gallery", "Photos", Icons.Default.SmartDisplay)
    data object TransitGuide : AppDestination("transit_guide", "Transit Hub", Icons.Default.DirectionsBus)
    data object Lifeline : AppDestination("lifeline", "Helplines", Icons.Default.LocalHospital)
    data object NightGuardian : AppDestination("night_guardian", "Night Guardian", Icons.Default.NightsStay)
    data object MeterCam : AppDestination("meter_cam", "Meter", Icons.Default.Speed)
    data object FairFare : AppDestination("fair_fare", "Ride Fares", Icons.Default.Calculate)
    data object OfficialFaresList : AppDestination("official_fares", "Official Fares", Icons.Default.Gavel)
    data object ProStore : AppDestination("pro_store", "Full Access", Icons.Default.Star)
}

private const val DISTRICT_ROUTE = "district/{id}"
private const val PLACE_ROUTE = "place/{id}"
private fun districtRoute(id: String) = "district/$id"
private fun placeRoute(id: String) = "place/$id"

@Composable
fun NammaAutoNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val tabs = listOf(
        AppDestination.ExploreKA,
        AppDestination.PlanHub,
        AppDestination.CommuteHub,
        AppDestination.KannadaSathi,
        AppDestination.SafetyHub
    )
    val go: (AppDestination) -> Unit = { navController.navigate(it.route) }
    fun AppDestination.tile(subtitle: String) = HubTile(label, subtitle, icon) { go(this) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Slate800,
                contentColor = Slate400
            ) {
                tabs.forEach { dest ->
                    NavigationBarItem(
                        selected = currentRoute == dest.route ||
                            (dest == AppDestination.ExploreKA && (currentRoute == DISTRICT_ROUTE || currentRoute == PLACE_ROUTE)),
                        onClick = {
                            navController.navigate(dest.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = dest.icon, contentDescription = dest.label) },
                        label = { Text(text = dest.label) },
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
                DistrictsHomeScreen(
                    viewModel = koinViewModel(),
                    onOpenDistrict = { navController.navigate(districtRoute(it)) },
                    onOpenPlace = { navController.navigate(placeRoute(it)) }
                )
            }
            composable(DISTRICT_ROUTE, arguments = listOf(navArgument("id") { type = NavType.StringType })) { entry ->
                val districtId = entry.arguments?.getString("id").orEmpty()
                DistrictScreen(
                    viewModel = koinViewModel(parameters = { parametersOf(districtId) }),
                    onBack = { navController.popBackStack() },
                    onOpenPlace = { navController.navigate(placeRoute(it)) }
                )
            }
            composable(PLACE_ROUTE, arguments = listOf(navArgument("id") { type = NavType.StringType })) { entry ->
                val placeId = entry.arguments?.getString("id").orEmpty()
                PlaceDetailScreen(
                    viewModel = koinViewModel(parameters = { parametersOf(placeId) }),
                    onBack = { navController.popBackStack() },
                    onReadSign = { go(AppDestination.SignTranslate) }
                )
            }
            composable(AppDestination.TravelGuide.route) {
                KarnatakaExplorerScreen(
                    viewModel = koinViewModel(),
                    onNavigateToPhrasebook = { go(AppDestination.Phrasebook) },
                    onNavigateToProStore = { go(AppDestination.ProStore) },
                    onNavigateToBusLens = { go(AppDestination.BusSignLens) },
                    onReadSign = { go(AppDestination.SignTranslate) }
                )
            }
            composable(AppDestination.PlanHub.route) {
                HubScreen(
                    title = "Plan your trip",
                    subtitle = "Food, budgets, weather and places you saved",
                    tiles = listOf(
                        AppDestination.TravelGuide.tile("Tickets, timings, dress codes, etiquette and scam alerts"),
                        AppDestination.TripCosts.tile("Budget for transport, stay, tickets and food"),
                        AppDestination.VegFood.tile("Trusted veg darshinis, highway stops, temple meals"),
                        AppDestination.LiveIntel.tile("Weather now, forest gate timings, place lookup"),
                        AppDestination.MediaGallery.tile("Photos and videos of Karnataka destinations"),
                        AppDestination.Favorites.tile("Places and restaurants you saved"),
                        AppDestination.ProStore.tile("One-time unlock: unlimited scans and voice")
                    )
                )
            }
            composable(AppDestination.CommuteHub.route) {
                HubScreen(
                    title = "Get around",
                    subtitle = "Fair fares, meters and buses",
                    tiles = listOf(
                        AppDestination.OfficialFaresList.tile("Government auto, cab, metro and bus fares for any distance"),
                        AppDestination.MeterCam.tile("GPS meter with the official fare, or scan the auto meter"),
                        AppDestination.FairFare.tile("Estimated auto and cab fares between areas"),
                        AppDestination.BusSignLens.tile("Point the camera at a bus or sign to read it in English"),
                        AppDestination.TransitGuide.tile("KSRTC, trains and city transit across Karnataka")
                    )
                )
            }
            composable(AppDestination.SafetyHub.route) {
                HubScreen(
                    title = "Stay safe",
                    subtitle = "Always free",
                    tiles = listOf(
                        AppDestination.NightGuardian.tile("Alerts if your ride moves away from your drop point; one-tap SOS"),
                        AppDestination.Lifeline.tile("Police, ambulance, hospitals and auto complaints")
                    )
                )
            }
            composable(AppDestination.VegFood.route) {
                VegFoodScreen(viewModel = koinViewModel())
            }
            composable(AppDestination.LiveIntel.route) {
                LiveTravelIntelScreen(viewModel = koinViewModel())
            }
            composable(AppDestination.TripCosts.route) {
                TravelCostCalculatorScreen(viewModel = koinViewModel())
            }
            composable(AppDestination.Favorites.route) {
                FavoritesScreen(
                    viewModel = koinViewModel(),
                    onNavigateToExplore = { go(AppDestination.ExploreKA) },
                    onNavigateToVegFood = { go(AppDestination.VegFood) }
                )
            }
            composable(AppDestination.BusSignLens.route) {
                BusSignboardLensScreen(
                    viewModel = koinViewModel(),
                    onBack = { navController.popBackStack() }
                )
            }
            composable(AppDestination.MediaGallery.route) {
                KarnatakaMediaShowcaseScreen()
            }
            composable(AppDestination.TransitGuide.route) {
                StatewideTravelNavigatorScreen(onNavigateToBusLens = { go(AppDestination.BusSignLens) })
            }
            composable(AppDestination.KannadaSathi.route) {
                HubScreen(
                    title = "Kannada",
                    subtitle = "Speak it and read it, offline",
                    tiles = listOf(
                        AppDestination.Phrasebook.tile("Ready phrases and English to Kannada with voice"),
                        AppDestination.SignTranslate.tile("Freeze the camera on a Kannada sign and read it in English")
                    )
                )
            }
            composable(AppDestination.SignTranslate.route) {
                BusSignboardLensScreen(
                    viewModel = koinViewModel(),
                    onBack = { navController.popBackStack() }
                )
            }
            composable(AppDestination.Phrasebook.route) {
                KannadaSathiScreen(
                    viewModel = koinViewModel(),
                    onNavigateToProStore = { go(AppDestination.ProStore) }
                )
            }
            composable(AppDestination.Lifeline.route) {
                LifelineScreen(viewModel = koinViewModel())
            }
            composable(AppDestination.NightGuardian.route) {
                NightGuardianScreen(viewModel = koinViewModel())
            }
            composable(AppDestination.MeterCam.route) {
                MeterCamScreen(
                    viewModel = koinViewModel(),
                    onNavigateToProStore = { go(AppDestination.ProStore) }
                )
            }
            composable(AppDestination.FairFare.route) {
                FareEstimatorScreen(
                    viewModel = koinViewModel(),
                    onOpenKannadaNegotiator = { go(AppDestination.Phrasebook) }
                )
            }
            composable(AppDestination.OfficialFaresList.route) {
                OfficialFaresScreen()
            }
            composable(AppDestination.ProStore.route) {
                ProStoreScreen(viewModel = koinViewModel())
            }
        }
    }
}
