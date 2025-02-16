package com.antsyferov.ui.analytics

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.koin.compose.koinInject
import kotlin.reflect.KProperty1
import kotlin.reflect.KType
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

inline fun <reified T: Any> NavGraphBuilder.composableWithAnalytics(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        null,
    noinline exitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        null,
    noinline popEnterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        enterTransition,
    noinline popExitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        exitTransition,
    noinline sizeTransform:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?)? =
        null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {

    composable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        content = { navBackStackEntry ->
            val route = navBackStackEntry.toRoute<T>()
            val analyticsHandler = koinInject<AnalyticsHandler>()

            LaunchedEffect(Unit) {
                route.getData()?.let { (destination, params) ->
                    analyticsHandler.logData(destination, params)
                }
            }

            content(navBackStackEntry)
        }
    )
}

inline fun <reified T : Any> T.getData(): Pair<AnalyticsDestination, Map<String, Any?>>? {
    T::class.findAnnotation<AnalyticsDestination>()?.let { destination ->

        val navParams = mutableMapOf<String, Any?>()
        T::class.members
            .filter { it.hasAnnotation<AnalyticsData>() }
            .filterIsInstance<KProperty1<Any, *>>()
            .forEach { property ->
                navParams[property.name] = property.get(this)
            }

        return destination to navParams
    }
    return null
}