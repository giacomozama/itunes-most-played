package me.zama.itunes_most_played.ui.screens.playlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import me.zama.itunes_most_played.ui.navigation.Screen

@Composable
fun PlayListScreen(
    navController: NavController,
    viewModel: PlayListScreenViewModel
) {

    val lazyPagingItems = viewModel.songsPagingData.collectAsLazyPagingItems()

    PlayListGrid(
        lazyPagingItems = lazyPagingItems,
        onSongClicked = {
            navController.navigate(Screen.WebView.route(it))
        }
    )
}