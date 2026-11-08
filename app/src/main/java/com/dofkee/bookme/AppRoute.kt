package com.dofkee.bookme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dofkee.bookme.ui.theme.booklists.BooklistScreen
import com.dofkee.bookme.ui.theme.booklists.BooklistsScreen
import com.dofkee.bookme.data.user.ProfileScreen
import com.dofkee.bookme.ui.theme.homepage.BottomBar
import com.dofkee.bookme.ui.theme.homepage.HomeScreen

object Routes {
    const val MAIN_PAGE = "main_page"
    const val BOOKLISTS = "booklists"
    const val ACCOUNT   = "account"
    const val SETTINGS  = "settings"

    const val COLLECTION_ARG_ID = "idCollection"
    const val COLLECTION = "collection/{$COLLECTION_ARG_ID}"
    fun collection(id: String) = "collection/$id"
}

fun NavHostController.navigateTab(route: String) {
    if (currentDestination?.route == route) return
    navigate(route) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavBackStackEntry.requireString(key: String): String =
    arguments?.getString(key) ?: error("Аргумент '$key' не передан")

@Composable
fun AppRoutes(modifier: Modifier = Modifier) {
    val router = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomBar(router) }
    ) { innerPadding ->
        NavHost(
            navController = router,
            startDestination = Routes.MAIN_PAGE,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.MAIN_PAGE) {
                HomeScreen(
                    onSettingsClick = { router.navigate(Routes.SETTINGS) },
                    onProfileClick  = { router.navigateTab(Routes.ACCOUNT) },
                    onBooklistsClick = { router.navigateTab(Routes.BOOKLISTS) }
                )
            }

            composable(Routes.BOOKLISTS) {
                BooklistsScreen(
                    modifier = Modifier,
                    onBack = { router.popBackStack() },
                    onCollectionClick = { id ->
                        router.navigate(Routes.collection(id))
                    }
                )
            }

            composable(
                route = Routes.COLLECTION,
                arguments = listOf(
                    navArgument(Routes.COLLECTION_ARG_ID) { type = NavType.StringType }
                )
            ) { entry ->
                val id = entry.requireString(Routes.COLLECTION_ARG_ID)
                BooklistScreen(
                    title = id,
                    onBack = { router.popBackStack() }
                )
            }

            composable(Routes.ACCOUNT) {
                ProfileScreen(
                    modifier = Modifier,
                    onBack = { router.popBackStack() }
                )
            }
        }
    }
}