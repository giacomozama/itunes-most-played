package me.zama.itunes_most_played.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.zama.itunes_most_played.data.source.paging.SongsPagingSource
import javax.inject.Inject

class SongsRepositoryImpl @Inject constructor(
    private val songsPagingSource: SongsPagingSource
) : SongsRepository {

    override fun getSongsPager() = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = true)
    ) {
        songsPagingSource
    }
}