package me.zama.itunes_most_played.data.repository

import androidx.paging.Pager
import me.zama.itunes_most_played.domain.model.Song

interface SongsRepository {

    fun getSongsPager(): Pager<Int, Song>
}