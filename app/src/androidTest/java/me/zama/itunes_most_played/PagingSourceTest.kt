package me.zama.itunes_most_played

import androidx.paging.PagingSource
import kotlinx.coroutines.test.runTest
import me.zama.itunes_most_played.data.source.SongServiceTestImpl
import me.zama.itunes_most_played.data.source.paging.SongsPagingSource
import me.zama.itunes_most_played.domain.model.makeTestSong
import org.junit.Test

class PagingSourceTest {

    private val songService = SongServiceTestImpl()

    @Test
    fun correctly_loads_first_page() = runTest {
        val pagingSource = SongsPagingSource(songService)
        val songs = (0..19).map { makeTestSong(it) }
        assert(
            PagingSource.LoadResult.Page(
                data = songs,
                prevKey = null,
                nextKey = 1
            ) == pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 20,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun correctly_loads_second_page_from_first() = runTest {
        val pagingSource = SongsPagingSource(songService)
        val songs = (20..39).map { makeTestSong(it) }
        assert(
            PagingSource.LoadResult.Page(
                data = songs,
                prevKey = 0,
                nextKey = 2
            ) == pagingSource.load(
                PagingSource.LoadParams.Append(
                    key = 1,
                    loadSize = 20,
                    placeholdersEnabled = false
                )
            )
        )
    }

}