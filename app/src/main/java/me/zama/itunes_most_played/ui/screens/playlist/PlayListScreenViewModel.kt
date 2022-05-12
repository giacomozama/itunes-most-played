package me.zama.itunes_most_played.ui.screens.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.zama.itunes_most_played.domain.use_case.GetSongsPagerUseCase
import javax.inject.Inject

@HiltViewModel
class PlayListScreenViewModel @Inject constructor(
    getSongsPagerUseCase: GetSongsPagerUseCase
) : ViewModel() {

    val songsPagingData = getSongsPagerUseCase.invoke().flow.cachedIn(viewModelScope)
}