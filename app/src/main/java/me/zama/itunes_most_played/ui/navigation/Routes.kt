package me.zama.itunes_most_played.ui.navigation

import androidx.annotation.StringRes
import me.zama.itunes_most_played.R

object Routes {

    const val Playlist = "playlist"
    const val WebView = "webview/{url}"

    @StringRes
    fun titleFor(route: String): Int = when (route) {
        Playlist -> R.string.play_list
        WebView -> R.string.song_details
        else -> throw IllegalArgumentException()
    }
}