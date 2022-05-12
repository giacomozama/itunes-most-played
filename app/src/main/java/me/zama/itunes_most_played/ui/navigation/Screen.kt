package me.zama.itunes_most_played.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    private val route: String,
    vararg params: Pair<String, NavType<*>>
) {

    val params = params.toList()

    fun route(vararg args: Any): String {
        if (args.isEmpty()) return route
        var cur = route
        var i = 0
        for ((name, _) in params) {
            cur = cur.replaceFirst("{$name}", args[i++].toString())
        }
        return cur
    }

    val navArguments get() = params.map { (name, type) -> navArgument(name) { this.type = type } }

    object Playlist : Screen(Routes.Playlist)

    object WebView : Screen(Routes.WebView, "url" to NavType.StringType)
}
