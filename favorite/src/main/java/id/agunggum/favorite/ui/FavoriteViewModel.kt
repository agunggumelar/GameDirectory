package id.agunggum.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.agunggum.core.domain.usecase.GameUseCase

class FavoriteViewModel (gameUseCase: GameUseCase): ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGame().asLiveData()
}