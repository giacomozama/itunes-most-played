package me.zama.itunes_most_played.domain.model


fun makeTestSong(id: Int) = Song(
    id = id.toString(),
    artistName = "Test Artist $id",
    name = "Test Song $id",
    releaseDate = "2022-05-12",
    artworkUrl100 = "",
    url = "about:blank"
)