package id.agunggum.gamedirectory.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.agunggum.core.domain.usecase.GameInteractor
import id.agunggum.core.domain.usecase.GameUseCase
import id.agunggum.gamedirectory.setting.PreferenceStorage
import id.agunggum.gamedirectory.setting.SettingPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideGameUseCase(gameInteractor: GameInteractor): GameUseCase

    @Binds
    @Singleton
    abstract fun providesPreferenceStorage(
        appPreferenceStorage: SettingPreferences
    ): PreferenceStorage
}