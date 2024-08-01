package com.task.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.task.profile.ProfileRoute

const val profileRoute = "profile_route"

fun NavGraphBuilder.profileScreen() {
    composable(route = profileRoute) {
        ProfileRoute()
    }
}


