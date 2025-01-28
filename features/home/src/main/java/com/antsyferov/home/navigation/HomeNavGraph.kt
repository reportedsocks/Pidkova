package com.antsyferov.home.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.antsyferov.cart.navigation.cartGraph
import com.antsyferov.products.navigation.productsGraph
import com.antsyferov.profile.navigation.profileGraph

val tabs = listOf(Products, Cart, Profile)

inline fun <reified T: Any> NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation<T>(startDestination = Tabs) {
        composable<Tabs> {
            Tabs()
        }
    }
}

@Composable
fun Tabs() {
    val tabsNavController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = tabsNavController,
            startDestination = Products,
            modifier = Modifier.weight(1f)
        ) {

            productsGraph<Products>(tabsNavController)

            cartGraph<Cart>(tabsNavController)

            profileGraph<Profile>(tabsNavController)

        }

        val navBackStackEntry by tabsNavController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val icons by remember { mutableStateOf(
            buildMap {
                put(Products, Icons.Default.Home)
                put(Cart, Icons.Default.ShoppingCart)
                put(Profile, Icons.Default.AccountCircle)
            }
        ) }

        NavigationBar {
            tabs.forEach { tab ->
                NavigationBarItem(
                    selected = currentDestination?.route == tab::class.qualifiedName,
                    icon = {
                        Icon(
                            imageVector = icons[tab] ?: Icons.Default.Error,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        tabsNavController.navigate(
                            route = tab,
                            navOptions = navOptions {
                                popUpTo(tabsNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                    },
                )
            }

        }
    }
}