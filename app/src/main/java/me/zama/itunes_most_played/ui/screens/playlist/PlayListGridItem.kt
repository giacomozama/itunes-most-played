package me.zama.itunes_most_played.ui.screens.playlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.zama.itunes_most_played.R

const val PLAY_LIST_GRID_ITEM_TEST_TAG = "play_list_grid_item"

@Composable
fun PlayListGridItem(
    modifier: Modifier = Modifier,
    artistName: String,
    songName: String,
    releaseDate: String,
    artworkUrl: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .testTag(PLAY_LIST_GRID_ITEM_TEST_TAG)
            .heightIn(min = dimensionResource(id = R.dimen.play_list_grid_item_height)),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.play_list_grid_item_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(artworkUrl)
                    .size(100)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.item_song_cover_corner_radius))),
                contentDescription = stringResource(R.string.artwork_for_template, songName)
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.play_list_grid_item_line_spacing)))
            Text(
                text = artistName,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = songName,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = releaseDate,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}