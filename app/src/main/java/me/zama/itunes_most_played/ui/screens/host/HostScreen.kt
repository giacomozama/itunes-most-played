package me.zama.itunes_most_played.ui.screens.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.zama.itunes_most_played.R
import me.zama.itunes_most_played.ui.navigation.Routes
import me.zama.itunes_most_played.ui.navigation.Screen
import me.zama.itunes_most_played.ui.screens.playlist.PlayListScreen
import me.zama.itunes_most_played.ui.screens.webview.WebViewScreen

@Composable
fun HostScreen() {

    val navController = rememberNavController()

    var title by remember { mutableStateOf(R.string.empty) }
    var isNavigateUpAvailable by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        navController.currentBackStackEntryFlow.collect { navBackStackEntry ->
            navBackStackEntry.destination.route?.let { title = Routes.titleFor(it) }
            isNavigateUpAvailable = navController.previousBackStackEntry != null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = title)) },
                navigationIcon = if (isNavigateUpAvailable) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                } else {
                    null
                },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 0.dp
            )
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.Playlist.route()
        ) {

            composable(route = Screen.Playlist.route()) {
                PlayListScreen(
                    navController = navController,
                    viewModel = hiltViewModel()
                )
            }

            composable(
                route = Screen.WebView.route(),
                arguments = Screen.WebView.navArguments
            ) {
                WebViewScreen(url = it.arguments!!.getString(Screen.WebView.params[0].first)!!)
            }
        }
    }
}