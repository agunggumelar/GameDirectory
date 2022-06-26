package id.agunggum.favorite

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.agunggum.favorite.ui.FavoriteFragment
import id.agunggum.gamedirectory.di.FavoriteModuleDependencies

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}