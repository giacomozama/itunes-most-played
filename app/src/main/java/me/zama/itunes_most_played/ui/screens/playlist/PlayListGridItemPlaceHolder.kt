package me.zama.itunes_most_played.ui.screens.playlist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import me.zama.itunes_most_played.R

@Composable
fun PlayListGridItemPlaceHolder(modifier: Modifier = Modifier) {

    Spacer(
        modifier = modifier
            .heightIn(min = dimensionResource(id = R.dimen.play_list_grid_item_height))
    )
}