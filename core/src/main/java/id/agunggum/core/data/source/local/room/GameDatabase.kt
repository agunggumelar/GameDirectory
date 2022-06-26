package id.agunggum.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.agunggum.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}