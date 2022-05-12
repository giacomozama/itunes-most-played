package me.zama.itunes_most_played.data.source.remote

import com.squareup.moshi.JsonClass
import me.zama.itunes_most_played.domain.model.Feed

@JsonClass(generateAdapter = true)
data class MostPlayedSongsResponse(val feed: Feed)