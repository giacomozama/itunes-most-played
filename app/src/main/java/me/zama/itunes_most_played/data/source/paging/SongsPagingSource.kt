package me.zama.itunes_most_played.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.zama.itunes_most_played.data.source.remote.SongService
import me.zama.itunes_most_played.domain.model.Song
import javax.inject.Inject

class SongsPagingSource @Inject constructor(
    private val songService: SongService
) : PagingSource<Int, Song>() {

    override fun getRefreshKey(state: PagingState<Int, Song>) = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        val page = params.key ?: 0
        val prevKey = if (page > 0) page - 1 else null
        val nextKey = if (page < 2) page + 1 else null
        return try {
            val response = songService.getMostPlayedSongs((page + 1) * 20)
            val songs = response.body()?.feed?.results?.subList(page * 20, (page + 1) * 20).orEmpty()
            LoadResult.Page(
                data = songs,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}