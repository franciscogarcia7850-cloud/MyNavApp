package com.example.mynavapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost (
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            Home(
                onNavigationProfile = {id, name ->
                    // Se envían los valores como argumentos
                    navController.navigate( route = Profile(userId = id, userName = name))
                }
            )
        }
        composable<Profile> {
            backStackEntry ->
            // Se reciben los argumentos
            val profileArgs: Profile = backStackEntry.toRoute()

            ProfileScreen (
                userId = profileArgs.userId,
                userName = profileArgs.userName,
                onLogout = {
                    // Regresa al login y limpia el back stack para que "atrás" no vuelva al perfil
                    navController.navigate(route = Home) {
                        popUpTo<Home> { inclusive = true }
                    }
                }
            )

        }
    }
}
