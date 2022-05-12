package me.zama.itunes_most_played.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Feed(val results: List<Song>)
