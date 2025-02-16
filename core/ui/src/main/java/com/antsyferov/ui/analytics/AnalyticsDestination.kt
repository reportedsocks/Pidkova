package com.antsyferov.ui.analytics

import com.antsyferov.ui.BuildConfig

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class AnalyticsDestination(
    val buildVariant: String = BuildConfig.FLAVOR,
    val client: String = "Android",
    val screen: String = "some_screen_name"
)

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class AnalyticsData
