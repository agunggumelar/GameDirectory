package id.agunggum.core.domain.usecase

import id.agunggum.core.domain.model.Game
import id.agunggum.core.domain.repository.IGameRepository
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: IGameRepository): GameUseCase {

    override fun getAllGame() = gameRepository.getAllGame()
    override fun getDetailGame(id: Int) = gameRepository.getDetailGame(id)
    override suspend fun insertGame(game: Game) = gameRepository.insertGame(game)
    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game) = gameRepository.setFavoriteGame(game)
}