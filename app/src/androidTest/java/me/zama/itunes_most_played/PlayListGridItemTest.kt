package me.zama.itunes_most_played

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.zama.itunes_most_played.domain.model.makeTestSong
import me.zama.itunes_most_played.ui.screens.playlist.PlayListGridItem
import me.zama.itunes_most_played.ui.theme.ITunesMostPlayedTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayListGridItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_play_list_grid_item_content_is_correct() {
        val song = makeTestSong(0)
        with (composeTestRule) {
            setContent {
                ITunesMostPlayedTheme {
                    PlayListGridItem(
                        artistName = song.artistName,
                        songName = song.name,
                        releaseDate = song.releaseDate,
                        artworkUrl = song.artworkUrl100,
                        onClick = {}
                    )
                }
            }
            onNodeWithText(song.artistName).assertIsDisplayed()
            onNodeWithText(song.name).assertIsDisplayed()
            onNodeWithText(song.releaseDate).assertIsDisplayed()
        }
    }
}
