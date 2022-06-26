package id.agunggum.core.data.source.local.room

import androidx.room.*
import id.agunggum.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games where isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGameById(id: Int): Flow<GameEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(tourism: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameEntity)

    @Update
    fun updateGame(game: GameEntity)
}