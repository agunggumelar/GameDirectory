package id.agunggum.core.data.source.local

import id.agunggum.core.data.source.local.entity.GameEntity
import id.agunggum.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao) {

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getGameById(id: Int): Flow<GameEntity>? = gameDao.getGameById(id)

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    suspend fun insertGame(game: GameEntity) = gameDao.insertGame(game)

    fun editGame(game: GameEntity) = gameDao.updateGame(game)
}