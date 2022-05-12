package me.zama.itunes_most_played.data.source

import me.zama.itunes_most_played.data.source.remote.MostPlayedSongsResponse
import me.zama.itunes_most_played.data.source.remote.SongService
import me.zama.itunes_most_played.domain.model.Feed
import me.zama.itunes_most_played.domain.model.makeTestSong
import retrofit2.Response

class SongServiceTestImpl : SongService {

    override suspend fun getMostPlayedSongs(maxItems: Int): Response<MostPlayedSongsResponse> = Response.success(
        MostPlayedSongsResponse(
            Feed(
                results = List(maxItems) {
                    makeTestSong(it)
                }
            )
        )
    )
}