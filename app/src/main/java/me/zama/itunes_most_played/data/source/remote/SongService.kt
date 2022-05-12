package me.zama.itunes_most_played.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SongService {

    @GET("most-played/{maxItems}/songs.json")
    suspend fun getMostPlayedSongs(@Path("maxItems") maxItems: Int): Response<MostPlayedSongsResponse>

    companion object {

        const val BaseUrl = "https://rss.applemarketingtools.com/api/v2/us/music/"
    }
}