package me.zama.itunes_most_played.ui.screens.playlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import me.zama.itunes_most_played.R
import me.zama.itunes_most_played.domain.model.Song
import me.zama.itunes_most_played.ui.extensions.items
import java.net.URLEncoder

const val PLAY_LIST_GRID_TEST_TAG = "play_list_grid"

@Composable
fun PlayListGrid(
    lazyPagingItems: LazyPagingItems<Song>,
    onSongClicked: (url: String) -> Unit
) {
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = Modifier.testTag(PLAY_LIST_GRID_TEST_TAG),
        state = lazyGridState,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.play_list_grid_content_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.play_list_grid_gap)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.play_list_grid_gap))
    ) {
        items(
            items = lazyPagingItems,
            key = { it.id }
        ) {
            if (it == null) {
                PlayListGridItemPlaceHolder()
            } else {
                PlayListGridItem(
                    artistName = it.artistName,
                    songName = it.name,
                    releaseDate = it.releaseDate,
                    artworkUrl = it.artworkUrl100
                ) {
                    onSongClicked(URLEncoder.encode(it.url, "utf-8"))
                }
            }
        }
    }
}