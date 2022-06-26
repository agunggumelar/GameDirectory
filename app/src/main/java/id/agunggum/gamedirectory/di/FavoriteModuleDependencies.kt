package id.agunggum.gamedirectory.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.agunggum.core.domain.usecase.GameUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun gameUseCase(): GameUseCase
}