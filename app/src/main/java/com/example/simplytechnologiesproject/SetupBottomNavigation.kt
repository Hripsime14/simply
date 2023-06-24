package com.example.simplytechnologiesproject

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.stringResource

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
    Surface(color = colorResource(id = R.color.gray_light)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Image(
                    painter = painterResource(id = R.drawable.morning),
                    contentDescription = "Top Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )
            }

            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.TopCenter) {
                Column(verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)) {
                    SetupTopView()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box{
                            SetupInfoView()
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.car),
                                contentDescription = "Image",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxWidth().offset(y = 100.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VehicleScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.vehicle))
    }
}

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.map))
    }
}

@Composable
fun SupportScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.support))
    }
}


@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.settings))
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