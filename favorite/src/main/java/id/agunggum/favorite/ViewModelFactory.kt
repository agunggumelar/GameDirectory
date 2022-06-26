package id.agunggum.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.agunggum.core.domain.usecase.GameUseCase
import id.agunggum.favorite.ui.FavoriteViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val gameUseCase: GameUseCase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unkwnown Viewmodel class: " + modelClass.name)
        }

}