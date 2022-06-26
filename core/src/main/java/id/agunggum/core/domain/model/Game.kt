package id.agunggum.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    var description : String,
    val released: String,
    val rating: Double,
    val imageUrl: String,
    val isFavorite: Boolean
): Parcelable