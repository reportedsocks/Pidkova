package com.antsyferov.home.navigation

import kotlinx.serialization.Serializable

//root
@Serializable
data object Tabs

//tab options
@Serializable
data object Products

@Serializable
data object Cart

@Serializable
data object Profile

val tabs = listOf(Products, Cart, Profile)
