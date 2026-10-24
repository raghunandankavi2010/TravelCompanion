package com.nammaauto.ai.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Security
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
import com.nammaauto.ai.core.theme.Slate700
import com.nammaauto.ai.core.theme.Slate800
import com.nammaauto.ai.core.theme.Slate900
import com.nammaauto.ai.presentation.calculator.FareEstimatorScreen
import com.nammaauto.ai.presentation.calculator.FareEstimatorViewModel
import com.nammaauto.ai.presentation.meter.MeterCamScreen
import com.nammaauto.ai.presentation.meter.MeterViewModel
import com.nammaauto.ai.presentation.phrasebook.KannadaSathiScreen
import com.nammaauto.ai.presentation.phrasebook.KannadaSathiViewModel
import com.nammaauto.ai.presentation.safety.NightGuardianScreen
import com.nammaauto.ai.presentation.safety.NightGuardianViewModel
import com.nammaauto.ai.presentation.store.ProStoreScreen
import com.nammaauto.ai.presentation.store.ProStoreViewModel

sealed class AppDestination(val route: String, val label: String, val icon: ImageVector) {
    data object MeterCam : AppDestination("meter_cam", "Meter Cam", Icons.Default.CameraAlt)
    data object FairFare : AppDestination("fair_fare", "Fair Fare", Icons.Default.Calculate)
    data object KannadaSathi : AppDestination("kannada", "Kannada", Icons.Default.RecordVoiceOver)
    data object NightRadar : AppDestination("night_radar", "Night Radar", Icons.Default.Security)
    data object ProStore : AppDestination("pro_store", "Pro Pass", Icons.Default.Star)
}

@Composable
fun NammaAutoNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        AppDestination.MeterCam,
        AppDestination.FairFare,
        AppDestination.KannadaSathi,
        AppDestination.NightRadar,
        AppDestination.ProStore
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Slate800,
                contentColor = Slate400
            ) {
                items.forEach { dest ->
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
            startDestination = AppDestination.MeterCam.route,
            modifier = Modifier.padding(innerPadding)
        ) {
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
            composable(AppDestination.KannadaSathi.route) {
                val vm: KannadaSathiViewModel = viewModel()
                KannadaSathiScreen(
                    viewModel = vm,
                    onNavigateToProStore = {
                        navController.navigate(AppDestination.ProStore.route)
                    }
                )
            }
            composable(AppDestination.NightRadar.route) {
                val vm: NightGuardianViewModel = viewModel()
                NightGuardianScreen(viewModel = vm)
            }
            composable(AppDestination.ProStore.route) {
                val vm: ProStoreViewModel = viewModel()
                ProStoreScreen(viewModel = vm)
            }
        }
    }
}
