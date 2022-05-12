package me.zama.itunes_most_played.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Song(
    val id: String,
    val artistName: String,
    val name: String,
    val releaseDate: String,
    val artworkUrl100: String,
    val url: String
)
