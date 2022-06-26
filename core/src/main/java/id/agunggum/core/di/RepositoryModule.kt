package id.agunggum.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.agunggum.core.data.GameRepository
import id.agunggum.core.domain.repository.IGameRepository


@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: GameRepository): IGameRepository

}