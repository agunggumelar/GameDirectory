package id.agunggum.core.domain.usecase

import id.agunggum.core.data.Resource
import id.agunggum.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<Resource<List<Game>>>
    fun getDetailGame(id: Int): Flow<Resource<Game>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(tourism: Game)
    suspend fun insertGame(game: Game)
}