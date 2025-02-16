package com.antsyferov.ui.analytics

import android.util.Log
import org.koin.core.annotation.Single
import java.util.Date

@Single
class AnalyticsHandler {
    //TODO connect analytics provider
    fun logData(destination: AnalyticsDestination, params: Map<String, Any?>) {
        val sb = StringBuilder()
        sb.append(
            "Navigation to '${destination.screen}' at '${Date().time}'; " +
                    "Build type = '${destination.buildVariant}'; " +
                    "Nav params: ${if (params.isEmpty()) "none" else ""}"
        )
        params.forEach { (key, value) ->
            sb.append("\n $key = '${value?.toString()}'")
        }
        Log.d("Navigation", sb.toString())
    }
}