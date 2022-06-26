package id.agunggum.gamedirectory.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.agunggum.core.domain.usecase.GameInteractor
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(gameUseCase: GameInteractor): ViewModel() {
    val game = gameUseCase.getAllGame().asLiveData()
}