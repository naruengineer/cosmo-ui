// NavGraph.kt
package com.yourcompany.cosmo.android.nav

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*

sealed class Dest(val route: String) {
    data object Universe : Dest("universe")
    data object Warp     : Dest("warp/{target}") { fun to(target: String) = "warp/$target" }
    data object Detail   : Dest("detail/{starId}") { fun to(id: String) = "detail/$id" }
}

@Composable
fun CosmoNavGraph(
    onExit: (() -> Unit)? = null
) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Dest.Universe.route) {
        composable(Dest.Universe.route) {
            com.yourcompany.cosmo.android.ui.universe.UniverseScreen(
                onStarClick = { nav.navigate(Dest.Detail.to(it)) },
                onWarpClick = { nav.navigate(Dest.Warp.to(it)) }
            )
        }
        composable(Dest.Warp.route, arguments = listOf(navArgument("target"){defaultValue = "andromeda"})) {
            com.yourcompany.cosmo.android.ui.warp.WarpScreen(
                onWarpFinished = { nav.popBackStack(); /* 戻って新銀河を表示 */ }
            )
        }
        composable(Dest.Detail.route, arguments = listOf(navArgument("starId"){defaultValue="s-1"})) {
            com.yourcompany.cosmo.android.ui.detail.StarDetailScreen(
                onBack = { nav.popBackStack() }
            )
        }
    }
}
