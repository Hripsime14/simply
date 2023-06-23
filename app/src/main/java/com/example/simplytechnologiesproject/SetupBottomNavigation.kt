package com.example.simplytechnologiesproject

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupBottomNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = ImageVector.vectorResource(id = R.drawable.home) //Icons.Default.Home TODO: fix icon
                    ),
                    BottomNavItem(
                        name = "Vehicle",
                        route = "vehicle",
                        icon = ImageVector.vectorResource(id = R.drawable.vehicle)
                    ),
                    BottomNavItem(
                        name = "Map",
                        route = "map",
                        icon = ImageVector.vectorResource(id = R.drawable.map)
                    ),
                    BottomNavItem(
                        name = "Support",
                        route = "support",
                        icon = ImageVector.vectorResource(id = R.drawable.support)
                    ),
                    BottomNavItem(
                        name = "Settings",
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
            HomeScreen()
        }
        composable("vehicle") {
            VehicleScreen()
        }
        composable("map") {
            MapScreen()
        }
        composable("support") {
            SupportScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home")
    }
}

@Composable
fun VehicleScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Vehicle")
    }
}

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Map")
    }
}

@Composable
fun SupportScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Support")
    }
}


@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings")
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
                            tint = if (selected) colorResource(id = R.color.blue) else colorResource(id = R.color.gray)
                        );
                        Text(
                            text = item.name,
                            modifier = Modifier.padding(top = 5.dp),
                            fontSize = 12.sp)
                    }
                }
            )
        }
    }
}