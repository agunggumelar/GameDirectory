package id.agunggum.gamedirectory.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.agunggum.core.domain.model.Game
import id.agunggum.core.domain.usecase.GameUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase): ViewModel() {
    fun getDetailGame(id: Int) = gameUseCase.getDetailGame(id).asLiveData()
    fun setFavoriteGame(game: Game) = gameUseCase.setFavoriteGame(game)
}