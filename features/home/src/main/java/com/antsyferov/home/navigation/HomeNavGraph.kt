package com.antsyferov.home.navigation

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.antsyferov.cart.navigation.cartGraph
import com.antsyferov.products.navigation.ProductDetails
import com.antsyferov.products.navigation.productsGraph
import com.antsyferov.profile.navigation.profileGraph

val tabs = listOf(Products, Cart, Profile)

inline fun <reified T: Any> NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation<T>(startDestination = HomeTabs()) {
        composable<HomeTabs>(
            deepLinks = listOf(navDeepLink<HomeTabs>(basePath = DEEPLINK_BASE_PATH))
        ) {
            val route = it.toRoute<HomeTabs>()
            Tabs(
                destination = route.destination,
                productId = route.productId
            )
        }
    }
}

@Composable
fun Tabs(
    destination: String?,
    productId: Long?
) {
    val tabsNavController = rememberNavController()

    DeepLinkHandler(
        navController = tabsNavController,
        destination = destination,
        productId = productId
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = tabsNavController,
            startDestination = Products,
            modifier = Modifier.weight(1f)
        ) {
            productsGraph<Products>(
                navController = tabsNavController,
                onNavToCart = {
                    tabsNavController.navigate(
                        route = Cart,
                        navOptions = navOptions {
                            changeTabNavOptions(navController = tabsNavController)
                        }
                    )
                }
            )

            cartGraph<Cart>(
                navController = tabsNavController,
                onNavigateToProductDetails = { productId ->
                    tabsNavController.navigate(
                        route = ProductDetails(productId),
                        navOptions = navOptions {
                            changeTabNavOptions(navController = tabsNavController)
                            restoreState = false
                        }
                    )
                }
            )

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
                    selected = isCurrentTabClicked(currentDestination, tab),
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
                                if (isCurrentTabClicked(currentDestination, tab)) {
                                    popCurrentTabNavOptions(tab)
                                } else {
                                    changeTabNavOptions(tabsNavController)
                                }
                            }
                        )
                    },
                )
            }

        }
    }
}

fun NavOptionsBuilder.changeTabNavOptions(
    navController: NavController
) {
    popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavOptionsBuilder.popCurrentTabNavOptions(
    tab: Tab
) {
    popUpTo(tab) {
        inclusive = true
    }
    launchSingleTop = true
}

fun isCurrentTabClicked(
    currentDestination: NavDestination?,
    clickedTab: Tab
): Boolean {
    return currentDestination?.parent?.route == clickedTab::class.qualifiedName
}