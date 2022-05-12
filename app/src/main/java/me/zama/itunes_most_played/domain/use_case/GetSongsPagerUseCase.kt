package me.zama.itunes_most_played.domain.use_case

import me.zama.itunes_most_played.data.repository.SongsRepository
import javax.inject.Inject

class GetSongsPagerUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) {

    fun invoke() = songsRepository.getSongsPager()
}