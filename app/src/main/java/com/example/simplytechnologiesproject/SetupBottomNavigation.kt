package com.example.simplytechnologiesproject

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.simplytechnologiesproject.screen.home.HomeScreen
import com.example.simplytechnologiesproject.screen.map.MapScreen
import com.example.simplytechnologiesproject.screen.settings.SettingsScreen
import com.example.simplytechnologiesproject.screen.support.SupportScreen
import com.example.simplytechnologiesproject.screen.vehicle.VehicleScreen
import com.example.simplytechnologiesproject.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupBottomNavigation(viewModel: HomeViewModel = viewModel()) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = stringResource(R.string.home),
                        route = "home",
                        icon = ImageVector.vectorResource(id = R.drawable.home) //Icons.Default.Home TODO: fix icon
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.vehicle),
                        route = "vehicle",
                        icon = ImageVector.vectorResource(id = R.drawable.vehicle)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.map),
                        route = "map",
                        icon = ImageVector.vectorResource(id = R.drawable.map)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.support),
                        route = "support",
                        icon = ImageVector.vectorResource(id = R.drawable.support)
                    ),
                    BottomNavItem(
                        name = stringResource(R.string.settings),
                        route = "settings",
                        icon = ImageVector.vectorResource(id = R.drawable.settings)
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                })
        }
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(colorId = R.color.gray_light,
                    drawableBackgroundId = R.drawable.morning,
                    drawableCarId = R.drawable.car)
        }
        composable("vehicle") {
            VehicleScreen(modifier = Modifier.fillMaxSize(),R.string.vehicle)
        }
        composable("map") {
            MapScreen(modifier = Modifier.fillMaxSize(),R.string.map)
        }
        composable("support") {
            SupportScreen(modifier = Modifier.fillMaxSize(), R.string.support)
        }
        composable("settings") {
            SettingsScreen(modifier = Modifier.fillMaxSize(), R.string.settings)
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White

    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,//navController.currentDestination?.route,
                colors = NavigationBarItemDefaults
                    .colors(
                        selectedIconColor = colorResource(id = R.color.blue),//40A0DA
                        unselectedIconColor = colorResource(id = R.color.gray),
                        indicatorColor = Color.White
                    ),
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Canvas(modifier = if (selected) Modifier //TODO: delete or fix
//                            .fillMaxWidth()
//                            .height(5.dp)
//                            .padding(top = 0.dp, bottom = 50.dp)
//                        else Modifier
//                        ) {
//                            drawLine(
//                                color = Color.Black,
//                                start = Offset(0f, 0f),
//                                end = Offset(size.width, 0f),
//                                strokeWidth = 2f
//                            )
//                        };
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            tint = if (selected) colorResource(id = R.color.blue) else colorResource(
                                id = R.color.gray
                            )
                        );
                        Text(
                            text = item.name,
                            modifier = Modifier.padding(top = 5.dp),
                            fontSize = 12.sp
                        )
                    }
                }
            )
        }
    }
}