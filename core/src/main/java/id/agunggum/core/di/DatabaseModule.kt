package id.agunggum.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.agunggum.core.data.source.local.room.GameDao
import id.agunggum.core.data.source.local.room.GameDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private val passphrase: ByteArray = SQLiteDatabase.getBytes("gamedirectory".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase = Room.databaseBuilder(
        context,
        GameDatabase::class.java, "Games.db"
    ).fallbackToDestructiveMigration().openHelperFactory(factory).build()

    @Provides
    fun provideTourismDao(database: GameDatabase): GameDao = database.gameDao()
}